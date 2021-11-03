package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.blockeduser.BlockedUser;
import com.orzechowski.aidme.entities.blockeduser.BlockedUserRowMapper;
import com.orzechowski.aidme.entities.category.Category;
import com.orzechowski.aidme.entities.category.CategoryRowMapper;
import com.orzechowski.aidme.entities.categorytag.CategoryTag;
import com.orzechowski.aidme.entities.categorytag.CategoryTagRowMapper;
import com.orzechowski.aidme.entities.document.Document;
import com.orzechowski.aidme.entities.document.DocumentRowMapper;
import com.orzechowski.aidme.entities.helper.*;
import com.orzechowski.aidme.entities.helpertag.HelperTag;
import com.orzechowski.aidme.entities.helpertag.HelperTagRowMapper;
import com.orzechowski.aidme.entities.instructionset.InstructionSet;
import com.orzechowski.aidme.entities.instructionset.InstructionSetRowMapper;
import com.orzechowski.aidme.entities.keyword.Keyword;
import com.orzechowski.aidme.entities.keyword.KeywordRowMapper;
import com.orzechowski.aidme.entities.multimedia.Multimedia;
import com.orzechowski.aidme.entities.multimedia.MultimediaRowMapper;
import com.orzechowski.aidme.entities.tag.Tag;
import com.orzechowski.aidme.entities.tag.TagRowMapper;
import com.orzechowski.aidme.entities.tagkeyword.TagKeyword;
import com.orzechowski.aidme.entities.tagkeyword.TagKeywordRowMapper;
import com.orzechowski.aidme.entities.tutorial.Tutorial;
import com.orzechowski.aidme.entities.tutorial.TutorialRowMapper;
import com.orzechowski.aidme.entities.tutoriallink.TutorialLink;
import com.orzechowski.aidme.entities.tutoriallink.TutorialLinkRowMapper;
import com.orzechowski.aidme.entities.tutorialsound.TutorialSound;
import com.orzechowski.aidme.entities.tutorialsound.TutorialSoundRowMapper;
import com.orzechowski.aidme.entities.tutorialtag.TutorialTag;
import com.orzechowski.aidme.entities.tutorialtag.TutorialTagRowMapper;
import com.orzechowski.aidme.entities.version.Version;
import com.orzechowski.aidme.entities.version.VersionRowMapper;
import com.orzechowski.aidme.entities.versioninstruction.VersionInstruction;
import com.orzechowski.aidme.entities.versioninstruction.VersionInstructionRowMapper;
import com.orzechowski.aidme.entities.versionmultimedia.VersionMultimedia;
import com.orzechowski.aidme.entities.versionmultimedia.VersionMultimediaRowMapper;
import com.orzechowski.aidme.entities.versionsound.VersionSound;
import com.orzechowski.aidme.entities.versionsound.VersionSoundRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RestController
public class UrlRestController
{
    //encoding guide:
    //xyz121 = .
    //xyz122 = @

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final ApplicationContext context;
    private final static String pathBase = "gs://aidme/";
    private final List<Helper> occupiedHelpers = new LinkedList<>();

    public UrlRestController(JdbcTemplate jdbcTemplate, ApplicationContext context)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<Login> login(@PathVariable String email)
    {
        email = email.replace("xyz121", ".").replace("xyz122", "@");
        Login loginResponse = jdbcTemplate.queryForObject("SELECT verified, helping FROM helper " +
                "WHERE helper_email = '" + email + "'", new HelperLoginMapper());
        if(loginResponse!=null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            jdbcTemplate.execute("INSERT INTO helper VALUES default, null, null, null, null, '" + email +
                    "', null, false, false");
            return ResponseEntity.ok(new Login(false, false));
        }
    }

    @GetMapping("/fullhelperdetailforemail/{email}")
    public ResponseEntity<Helper> fullDetail(@PathVariable String email)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForObject("SELECT * FROM helper WHERE helper_email = '" +
                    email.replace("xyz121", ".").replace("xyz122", "@") + "'",
                    new HelperFullMapper()));
        } catch(DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/setfullhelperdetailforemail/{email}/{id}/{name}/{surname}/{title}/{profession}/{phone}")
    public ResponseEntity<Boolean> uploadDetail(@PathVariable String email, @PathVariable String id,
                                                @PathVariable String name, @PathVariable String surname,
                                                @PathVariable String title, @PathVariable String profession,
                                                @PathVariable String phone)
    {
        String query = "UPDATE helper SET helper_name = '" + name + "', helper_surname = '" + surname + "'";
        if(!Objects.equals(title, "null")) {
            query = query + ", helper_title = '" + title + "'";
        }
        if(!Objects.equals(profession, "null")) {
            query = query + ", helper_profession = '" + profession + "'";
        }
        if(!Objects.equals(phone, "null")) {
            query = query + ", helper_phone = '" + phone + "'";
        }
        jdbcTemplate.execute(query + " WHERE helper_id = " + id + " AND helper_email = '" + email + "'");
        return ResponseEntity.ok(true);
    }

    @GetMapping("/helperlist")
    public ResponseEntity<List<Helper>> helperlist()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT helper_id, helper_name, " +
                    "helper_surname, helper_title, helper_profession FROM helper", new HelperBasicMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/number/{id}")
    public ResponseEntity<Helper> helperNumber(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForObject("SELECT h.* FROM helpers h" +
                            "JOIN helper_tags ht ON h.helper_id = ht.helper_id " +
                            "JOIN tags t ON ht.tag_id = t.tag_id WHERE t.tag_id = " + id, new HelperFullMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> tutorials()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial WHERE approved = t",
                    new TutorialRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM category", new CategoryRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> tags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tag", new TagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<Keyword>> keywords()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM keyword", new KeywordRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tagkeywords")
    public ResponseEntity<List<TagKeyword>> tagKeywords()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tag_keyword", new TagKeywordRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<BlockedUser>> blockedUsers()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM blocked_user", new BlockedUserRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/categorytags")
    public ResponseEntity<List<CategoryTag>> categoryTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM category_tag", new CategoryTagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/helpertags")
    public ResponseEntity<List<HelperTag>> helperTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM helper_tag", new HelperTagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialtags")
    public ResponseEntity<List<TutorialTag>> tutorialTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_tag", new TutorialTagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialinstructions/{id}")
    public ResponseEntity<List<InstructionSet>> tutorialInstructions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM instruction_set WHERE tutorial_id = " + id,
                    new InstructionSetRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/instructions")
    public ResponseEntity<List<InstructionSet>> allInstructions()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM instruction_set",
                    new InstructionSetRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versions")
    public ResponseEntity<List<Version>> versions()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version", new VersionRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versions/{id}")
    public ResponseEntity<List<Version>> tutorialVersions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version WHERE tutorial_id = " + id,
                    new VersionRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versioninstructions")
    public ResponseEntity<List<VersionInstruction>> versionInstructions()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version_instruction",
                    new VersionInstructionRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versionmultimedia/{id}")
    public ResponseEntity<List<VersionMultimedia>> versionMultimedia(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version_multimedia WHERE version_id = " + id,
                    new VersionMultimediaRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versionsound/{id}")
    public ResponseEntity<List<VersionSound>> versionSounds(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version_sound WHERE version_id = " + id,
                    new VersionSoundRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/multimedia/{id}")
    public ResponseEntity<List<Multimedia>> multimedia(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM multimedia WHERE tutorial_id = " + id,
                    new MultimediaRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/tutorialsounds")
    public ResponseEntity<List<TutorialSound>> sounds()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_sound",
                    new TutorialSoundRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutoriallink/{id}")
    public ResponseEntity<List<TutorialLink>> tutorialLinks(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_link WHERE origin_id = " + id,
                    new TutorialLinkRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutoriallinks")
    public ResponseEntity<List<TutorialLink>> allTutorialLinks()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_link",
                    new TutorialLinkRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/documentexists/{email}")
    public ResponseEntity<Document> checkDocument(@PathVariable String email)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForObject("SELECT d.* FROM document d JOIN helper h ON d.helper_id = " +
                                    "h.helper_id WHERE h.helper_email = " + email
                                    .replace("xyz121", ".").replace("xyz122", "@"),
                            new DocumentRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/userdocumentuploadimage")
    public ResponseEntity<Boolean> uploadDocument(@RequestParam("image") MultipartFile file)
    {
        try {
            File imageFile = new File(pathBase + "docs/" + file.getName() + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(file.getBytes());
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/tutorialcreationuploadimagemultimedia/{name}")
    public ResponseEntity<Boolean> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable String name)
    {
        try {
            File imageFile = new File(pathBase + "images/" + name + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(file.getBytes());
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/files/images/{filename}")
    public ResponseEntity<Resource> image(@PathVariable String filename)
    {
        HttpHeaders header = makeHeader(filename);
        try {
            Resource resource = context.getResource(pathBase + "images/" + filename);
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/files/vids/{filename}")
    public ResponseEntity<Resource> vid(@PathVariable String filename)
    {
        HttpHeaders header = makeHeader(filename);
        try {
            Resource resource = context.getResource(pathBase + "vids/" + filename);
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/files/sounds/{filename}")
    public ResponseEntity<Resource> sound(@PathVariable String filename)
    {
        HttpHeaders header = makeHeader(filename);
        try {
            Resource resource = context.getResource(pathBase + "sounds/" + filename);
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/files/narrations/{filename}")
    public ResponseEntity<Resource> narration(@PathVariable String filename)
    {
        HttpHeaders header = makeHeader(filename);
        try {
            Resource resource = context.getResource(pathBase + "narrations/" + filename);
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpHeaders makeHeader(String filename)
    {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }

    @PostMapping(path = "/create/tutorial", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutorial> insertTutorial(@PathVariable String email, @RequestBody Tutorial tutorial)
    {
        if(email==null || email.isEmpty() || jdbcTemplate.query("SELECT * FROM helper WHERE helper_email = " + email
                        .replace("xyz121", ".").replace("xyz122", "@"),
                new HelperFullMapper()).isEmpty() || queryUsersTutorial(tutorial)==null) {
            return null;
        }
        jdbcTemplate.execute("INSERT INTO tutorials VALUES(default, " + tutorial.getTutorialName() +
                ", " + tutorial.getAuthorId() + ", " + tutorial.getMiniatureName() + ", 0, 'f';");
        try {
            return ResponseEntity.ok(queryUsersTutorial(tutorial));
        } catch (DataAccessException e) {
            return null;
        }
    }

    @GetMapping(path = "/help/{email}/{help}")
    public ResponseEntity<Helping> toggleHelp(@PathVariable String email, @PathVariable String help)
    {
        email = email.replace("xyz121", ".").replace("xyz122", "@");
        if(Objects.equals(help, "t")) {
            this.jdbcTemplate.execute("UPDATE helper SET helping = 't' WHERE helper_email = '" + email + "'");
            return ResponseEntity.ok(new Helping(true));
        } else {
            this.jdbcTemplate.execute("UPDATE helper SET helping = 'f' WHERE helper_email = '" + email + "'");
            return ResponseEntity.ok(new Helping(false));
        }
    }

    private Tutorial queryUsersTutorial(Tutorial tutorial)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE tutorial_name = '" +
                        tutorial.getTutorialName() + "' AND author_id = '" + tutorial.getAuthorId() + "'",
                new TutorialRowMapper());
    }
}
