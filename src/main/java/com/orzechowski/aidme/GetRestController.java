package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.blockeduser.BlockedUser;
import com.orzechowski.aidme.entities.blockeduser.BlockedUserRowMapper;
import com.orzechowski.aidme.entities.category.Category;
import com.orzechowski.aidme.entities.category.CategoryRowMapper;
import com.orzechowski.aidme.entities.categorytag.CategoryTag;
import com.orzechowski.aidme.entities.categorytag.CategoryTagRowMapper;
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

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RestController
public class GetRestController
{
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final ApplicationContext context;
    private final static String pathBase = "gs://aidme/";
    private final List<Helper> occupiedHelpers = new LinkedList<>();
    private final Decoder decoder = new Decoder();

    public GetRestController(JdbcTemplate jdbcTemplate, ApplicationContext context)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
        Helper admin = jdbcTemplate.queryForObject("SELECT * FROM helper WHERE helper_profession = 'admin'",
                new HelperFullMapper());
        occupiedHelpers.add(admin);
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<Login> login(@PathVariable String email)
    {
        email = decoder.decodeEmail(email);
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
                    decoder.decodeEmail(email) + "'", new HelperFullMapper()));
        } catch(DataAccessException e) {
            e.printStackTrace();
            return null;
        }
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
            return ResponseEntity.ok(jdbcTemplate.queryForObject("SELECT h.* FROM helper h" +
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
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial WHERE approved = 'true'",
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

    @GetMapping("/versionmultimedia")
    public ResponseEntity<List<VersionMultimedia>> versionMultimedia()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version_multimedia",
                    new VersionMultimediaRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versionsound")
    public ResponseEntity<List<VersionSound>> versionSounds()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version_sound",
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
    public String checkDocument(@PathVariable String email)
    {
        try {
            if(!jdbcTemplate.query("SELECT * FROM document WHERE document_file_name = '" + email + "'",
                    new DocumentRowMapper()).isEmpty()) {
                return "ok";
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
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

    @GetMapping("/help/{email}/{help}")
    public ResponseEntity<Helping> toggleHelp(@PathVariable String email, @PathVariable String help)
    {
        email = decoder.decodeEmail(email);
        if(Objects.equals(help, "t")) {
            this.jdbcTemplate.execute("UPDATE helper SET helping = 't' WHERE helper_email = '" + email + "'");
            return ResponseEntity.ok(new Helping(true));
        } else {
            this.jdbcTemplate.execute("UPDATE helper SET helping = 'f' WHERE helper_email = '" + email + "'");
            return ResponseEntity.ok(new Helping(false));
        }
    }

    @GetMapping("/phonenumber/{tagId}")
    public ResponseEntity<Helper> requestPhoneNumber(@PathVariable Long tagId)
    {
        try {
            List<Helper> helpers = jdbcTemplate.query("SELECT * FROM helper h JOIN helper_tag ht ON h.helper_id =" +
                    "ht.helper_id WHERE h.helping = 'true' AND ht.tag_id = " + tagId, new HelperFullMapper());
            helpers.removeIf(occupiedHelpers::contains);
            if(!helpers.isEmpty()) {
                Helper chosen = helpers.get(0);
                occupiedHelpers.add(chosen);
                return ResponseEntity.ok(chosen);
            } else return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/callended/{email}")
    public ResponseEntity<Boolean> removeFromOccupied(@PathVariable String email)
    {
        try {
            occupiedHelpers.remove(jdbcTemplate.queryForObject("SELECT * FROM helper WHERE helper_email = " +
                            decoder.decodeEmail(email), new HelperFullMapper()));
            return ResponseEntity.ok(true);
        } catch (DataAccessException e) {
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
}
