package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.*;
import com.orzechowski.aidme.entities.blockeduser.BlockedUser;
import com.orzechowski.aidme.entities.category.Category;
import com.orzechowski.aidme.entities.categorytag.CategoryTag;
import com.orzechowski.aidme.entities.helper.Helper;
import com.orzechowski.aidme.entities.helpertag.HelperTag;
import com.orzechowski.aidme.entities.instructionset.InstructionSet;
import com.orzechowski.aidme.entities.keyword.Keyword;
import com.orzechowski.aidme.entities.multimedia.Multimedia;
import com.orzechowski.aidme.entities.tag.Tag;
import com.orzechowski.aidme.entities.tagkeyword.TagKeyword;
import com.orzechowski.aidme.entities.tutorial.Tutorial;
import com.orzechowski.aidme.entities.tutoriallink.TutorialLink;
import com.orzechowski.aidme.entities.tutorialsound.TutorialSound;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class UrlRestController
{
    private final JdbcTemplate jdbcTemplate;
    private final static String pathBase = "gs://aidme/";
    private final List<Helper> occupiedHelpers = new LinkedList<>();

    public UrlRestController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
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
    public ResponseEntity<Boolean> uploadDetail(@RequestBody ServerSideHelper helper)
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
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT helper_id, helper_name, " +
                    "helper_surname, helper_title, helper_profession FROM helpers", Helper.class));
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
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM tutorials", Tutorial.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM categories", Category.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> tags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM tags", Tag.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<Keyword>> keywords()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM keywords", Keyword.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tagkeywords")
    public ResponseEntity<List<TagKeyword>> tagKeywords()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM tag_keywords", TagKeyword.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<BlockedUser>> blockedUsers()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM blocked_users", BlockedUser.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/categorytags")
    public ResponseEntity<List<CategoryTag>> categoryTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM category_tags", CategoryTag.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/helpertags")
    public ResponseEntity<List<HelperTag>> helperTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM helper_tags", HelperTag.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialtags")
    public ResponseEntity<List<TutorialTag>> tutorialTags()
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM tutorial_tags", TutorialTag.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialinstructions/{id}")
    public ResponseEntity<List<InstructionSet>> tutorialInstructions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForList("SELECT * FROM instruction_sets WHERE tutorial_id = " + id,
                            InstructionSet.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutorialversions/{id}")
    public ResponseEntity<List<Version>> tutorialVersions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate.queryForList("SELECT * FROM versions WHERE tutorial_id = " + id,
                    Version.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versioninstructions/{id}")
    public ResponseEntity<List<VersionInstruction>> versionInstructions(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForList("SELECT * FROM version_instructions WHERE version_id = " + id,
                            VersionInstruction.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versionmultimedia/{id}")
    public ResponseEntity<List<VersionMultimedia>> versionMultimedia(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForList("SELECT * FROM version_multimedia WHERE version_id = " + id,
                            VersionMultimedia.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/versionsound/{id}")
    public ResponseEntity<List<VersionSound>> versionSounds(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForList("SELECT * FROM version_sound WHERE version_id = " + id,
                            VersionSound.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/multimedia/{id}")
    public ResponseEntity<Multimedia> multimedia(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForObject("SELECT * FROM multimedia WHERE multimedia_id = " + id,
                            Multimedia.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/sound/{id}")
    public ResponseEntity<TutorialSound> sounds(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForObject("SELECT * FROM tutorial_sounds WHERE sound_id = " + id,
                            TutorialSound.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/tutoriallink/{id}")
    public ResponseEntity<List<TutorialLink>> tutoriallinks(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok(jdbcTemplate
                    .queryForList("SELECT * FROM tutorial_links WHERE origin_id = " + id,
                            TutorialLink.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/files/images/{filename}")
    public ResponseEntity<Resource> image(@PathVariable String filename)
    {
        File file = new File(pathBase + "images\\" + filename);
        HttpHeaders header = makeHeader(filename);
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
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
        File file = new File(pathBase + "vids\\" + filename);
        HttpHeaders header = makeHeader(filename);
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
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
        File file = new File(pathBase + "sounds\\" + filename);
        HttpHeaders header = makeHeader(filename);
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
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
        File file = new File(pathBase + "narrations\\" + filename);
        HttpHeaders header = makeHeader(filename);
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
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
}