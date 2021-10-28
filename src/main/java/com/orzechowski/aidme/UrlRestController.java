package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.blockeduser.BlockedUser;
import com.orzechowski.aidme.entities.blockeduser.BlockedUserRowMapper;
import com.orzechowski.aidme.entities.category.Category;
import com.orzechowski.aidme.entities.category.CategoryRowMapper;
import com.orzechowski.aidme.entities.categorytag.CategoryTag;
import com.orzechowski.aidme.entities.categorytag.CategoryTagRowMapper;
import com.orzechowski.aidme.entities.helper.Helper;
import com.orzechowski.aidme.entities.helper.HelperRowMapper;
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

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class UrlRestController
{
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

    @PostMapping("/login")
    public ResponseEntity<List<Boolean>> login(@RequestParam String email)
    {
        List<Boolean> status = new LinkedList<>();
        List<String> loginResponse = this.jdbcTemplate.queryForList("SELECT helping, verified " +
                "WHERE email = " + email).stream().map((m) -> m.values().toString()).collect(Collectors.toList());
        if(!loginResponse.isEmpty()) {
            status.add(true);
            if(Objects.equals(loginResponse.get(0), "true")) status.add(true);
            if(Objects.equals(loginResponse.get(1), "true")) status.add(true);
        } else {
            status.add(false);
        }
        return ResponseEntity.ok(status);
    }

    @PutMapping("/setFullHelperDetail")
    public ResponseEntity<Boolean> uploadDetail(@RequestBody Helper helper)
    {
        this.jdbcTemplate.execute("INSERT INTO helpers VALUES " +
                    "default, '"
                    + helper.getName() + "', '"
                    + helper.getSurname() + "', '"
                    + helper.getTitle() + "', '"
                    + helper.getProfession() + "', '"
                    + helper.getEmail() + "', "
                    + helper.getPhone() + ", 'false', 'false'");
        return ResponseEntity.ok(true);
    }

    @GetMapping("/helperlist")
    public ResponseEntity<List<Helper>> helperlist()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT helper_id, helper_name, " +
                    "helper_surname, helper_title, helper_profession FROM helpers", new HelperRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
/*
    @GetMapping("/number/{id}")
    public ResponseEntity<Helper> helperNumber(@PathVariable long id)
    {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT h.* FROM helpers h " +
                            "JOIN helper_tags ht ON h.helper_id = ht.helper_id " +
                            "JOIN tags t ON ht.tag_id = t.tag_id WHERE t.tag_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Helper helper = new Helper(rs.getInt("helper_id"), rs.getString("helper_name"),
                        rs.getString("helper_surname"), rs.getString("helper_title"),
                        rs.getString("helper_profession"), rs.getInt("phone"));
                if(!occupiedHelpers.contains(helper)) {
                    occupiedHelpers.add(helper);
                    return ResponseEntity.ok(helper);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> tutorials()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorials", new TutorialRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM categories", new CategoryRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> tags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tags", new TagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<Keyword>> keywords()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM keywords", new KeywordRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tagkeywords")
    public ResponseEntity<List<TagKeyword>> tagKeywords()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tag_keywords", new TagKeywordRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<BlockedUser>> blockedUsers()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM blocked_users", new BlockedUserRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/categorytags")
    public ResponseEntity<List<CategoryTag>> categoryTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM category_tags", new CategoryTagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/helpertags")
    public ResponseEntity<List<HelperTag>> helperTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM helper_tags", new HelperTagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialtags")
    public ResponseEntity<List<TutorialTag>> tutorialTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_tags", new TutorialTagRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialinstructions/{id}")
    public ResponseEntity<List<InstructionSet>> tutorialInstructions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM instruction_sets WHERE tutorial_id = " + id,
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
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM instruction_sets",
                    new InstructionSetRowMapper()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialversions/{id}")
    public ResponseEntity<List<Version>> tutorialVersions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM versions WHERE tutorial_id = " + id,
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
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM version_instructions",
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
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_sounds",
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
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_links WHERE origin_id = " + id,
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
            return ResponseEntity.ok(jdbcTemplate.query("SELECT * FROM tutorial_links",
                    new TutorialLinkRowMapper()));
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
            Resource resource = context.getResource(pathBase + "videos/" + filename);
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
    public ResponseEntity<Tutorial> insertTutorial(@PathVariable String email, @RequestBody Tutorial tutorial) {
        if(email==null || email.isEmpty() || jdbcTemplate.query("SELECT * FROM helpers WHERE email = " + email,
                new HelperRowMapper()).isEmpty() || queryUsersTutorial(tutorial)==null) {
            return null;
        }
        jdbcTemplate.execute("INSERT INTO tutorials VALUES(default, " + tutorial.getTutorialName() +
                ", " + tutorial.getAuthorId() + ", " + tutorial.getMiniatureName() + ", 0;");
        try {
            return ResponseEntity.ok(queryUsersTutorial(tutorial));
        } catch (DataAccessException e) {
            return null;
        }
    }

    private Tutorial queryUsersTutorial(Tutorial tutorial) {
        return jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE tutorial_name = " +
                        tutorial.getTutorialName() + " AND author_id = " + tutorial.getAuthorId(),
                new TutorialRowMapper());
    }
}
