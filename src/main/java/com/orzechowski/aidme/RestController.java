package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    private final static String url = "jdbc:postgresql://localhost:5432/AidMe";
    private final static String user = "spring";
    private final static String password = "malina1213141516171819";
    private final static String pathBase = "C:\\server_files\\";
    private final List<Helper> occupiedHelpers = new LinkedList<>();

    @PostMapping("/login")
    public ResponseEntity<List<Boolean>> login(@RequestParam String email)
    {
        List<Boolean> status = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT helping, verified " +
                    "WHERE email = " + email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                status.add(true); //exists?
                status.add(rs.getBoolean("helping"));
                status.add(rs.getBoolean("verified"));
            } else {
                status.add(false); //exists?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(status);
    }

    @PutMapping("/setFullHelperDetail")
    public ResponseEntity<Boolean> uploadDetail(@RequestBody ServerSideHelper helper)
    {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO helpers VALUES " +
                    "default, '"
                    + helper.getName() + "', '"
                    + helper.getSurname() + "', '"
                    + helper.getTitle() + "', '"
                    + helper.getProfession() + "', '"
                    + helper.getEmail() + "', "
                    + helper.getPhone() + ", 'false', 'false'");
            preparedStatement.executeQuery();
            return ResponseEntity.ok(true);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/")
    public String home()
    {
        return "";
    }

    @GetMapping("/helperlist")
    public ResponseEntity<List<Helper>> helperlist()
    {
        List<Helper> helpers = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT helper_id, helper_name, " +
                    "helper_surname, helper_title, helper_profession FROM helpers");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                helpers.add(new Helper(rs.getInt("helper_id"), rs.getString("helper_name"),
                        rs.getString("helper_surname"), rs.getString("helper_title"),
                        rs.getString("helper_profession")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(helpers);
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
        List<Tutorial> tutorials = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tutorials");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tutorials.add(new Tutorial(rs.getInt("tutorial_id"), rs.getString("tutorial_name"),
                        rs.getInt("author_id"), rs.getString("miniature_name"),
                        rs.getFloat("rating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(tutorials);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories()
    {
        List<Category> categories = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("category_id"),
                        rs.getString("category_name"), rs.getBoolean("has_subcategories"),
                        rs.getString("miniature_name"), rs.getInt("category_level")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> tags()
    {
        List<Tag> tags = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tags");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tags.add(new Tag(rs.getInt("tag_id"), rs.getString("tag_name"),
                        rs.getInt("tag_level")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<Keyword>> keywords()
    {
        List<Keyword> keywords = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keywords");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                keywords.add(new Keyword(rs.getInt("keyword_id"), rs.getString("word")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(keywords);
    }

    @GetMapping("/tagkeywords")
    public ResponseEntity<List<TagKeyword>> tagKeywords()
    {
        List<TagKeyword> tagKeywords = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tag_keywords");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tagKeywords.add(new TagKeyword(rs.getInt("tag_keyword_id"), rs.getInt("tag_id"),
                        rs.getInt("keyword_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(tagKeywords);
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<BlockedUser>> blockedUsers()
    {
        List<BlockedUser> blockedUsers = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM blocked_users");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                blockedUsers.add(new BlockedUser(rs.getInt("blocked_user_id"),
                        rs.getString("phone_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(blockedUsers);
    }

    @GetMapping("/categorytags")
    public ResponseEntity<List<CategoryTag>> categoryTags()
    {
        List<CategoryTag> categoryTags = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category_tags");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categoryTags.add(new CategoryTag(rs.getInt("category_tag_id"),
                        rs.getInt("category_id"), rs.getInt("tag_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(categoryTags);
    }

    @GetMapping("/helpertags")
    public ResponseEntity<List<HelperTag>> helperTags()
    {
        List<HelperTag> helperTags = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM helper_tags");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                helperTags.add(new HelperTag(rs.getInt("helper_tag_id"), rs.getInt("helper_id"),
                        rs.getInt("tag_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(helperTags);
    }

    @GetMapping("/tutorialtags")
    public ResponseEntity<List<TutorialTag>> tutorialTags()
    {
        List<TutorialTag> tutorialTags = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tutorial_tags");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tutorialTags.add(new TutorialTag(rs.getInt("tutorial_tag_id"),
                        rs.getInt("tutorial_id"), rs.getInt("tag_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(tutorialTags);
    }

    @GetMapping("/tutorialinstructions/{id}")
    public ResponseEntity<List<InstructionSet>> tutorialInstructions(@PathVariable long id)
    {
        List<InstructionSet> instructionSets = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM instruction_sets WHERE tutorial_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                instructionSets.add(new InstructionSet(rs.getInt("instruction_set_id"),
                        rs.getString("title"), rs.getString("instructions"),
                        rs.getInt("time"), rs.getInt("tutorial_id"),
                        rs.getInt("set_position"), rs.getString("narration_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(instructionSets);
    }

    @GetMapping("/tutorialversions/{id}")
    public ResponseEntity<List<Version>> tutorialVersions(@PathVariable long id)
    {
        List<Version> versions = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM versions WHERE tutorial_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                versions.add(new Version(rs.getInt("version_id"), rs.getString("text"),
                        rs.getInt("tutorial_id"), rs.getBoolean("delay_global_sound"),
                        rs.getBoolean("has_children"), rs.getBoolean("has_parent"),
                        rs.getInt("parent_version_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(versions);
    }

    @GetMapping("/versioninstructions/{id}")
    public ResponseEntity<List<VersionInstruction>> versionInstructions(@PathVariable long id)
    {
        List<VersionInstruction> versionInstructions = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM version_instructions WHERE version_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                versionInstructions.add(new VersionInstruction(rs.getInt("version_instruction_id"), id,
                        rs.getInt("instruction_position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(versionInstructions);
    }

    @GetMapping("/versionmultimedia/{id}")
    public ResponseEntity<List<VersionMultimedia>> versionMultimedia(@PathVariable long id)
    {
        List<VersionMultimedia> versionMultimedia = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM version_multimedia WHERE version_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                versionMultimedia.add(new VersionMultimedia(rs.getInt("version_multimedia_id"),
                        rs.getInt("version_id"), rs.getInt("multimedia_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(versionMultimedia);
    }

    @GetMapping("/versionsound/{id}")
    public ResponseEntity<List<VersionSound>> versionSounds(@PathVariable long id)
    {
        List<VersionSound> versionSounds = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM version_sound WHERE version_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                versionSounds.add(new VersionSound(rs.getInt("version_sound_id"), id,
                        rs.getInt("sound_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(versionSounds);
    }

    @GetMapping("/multimedia/{id}")
    public ResponseEntity<Multimedia> multimedia(@PathVariable long id)
    {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM multimedia WHERE multimedia_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return ResponseEntity.ok(new Multimedia(id, rs.getInt("tutorial_id"),
                        rs.getInt("display_time"), rs.getBoolean("multimedia_type"),
                        rs.getString("file_name"), rs.getBoolean("multimedia_loop"),
                        rs.getInt("multimedia_position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/sound/{id}")
    public ResponseEntity<TutorialSound> sounds(@PathVariable long id)
    {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM tutorial_sounds WHERE sound_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return ResponseEntity.ok(new TutorialSound(id, rs.getInt("sound_start"),
                        rs.getBoolean("sound_loop"), rs.getInt("interval"),
                        rs.getInt("tutorial_id"), rs.getString("file_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/tutoriallink/{id}")
    public ResponseEntity<List<TutorialLink>> tutoriallinks(@PathVariable long id)
    {
        List<TutorialLink> tutorialLinks = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM tutorial_links WHERE origin_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                tutorialLinks.add(new TutorialLink(rs.getInt("tutorial_link_id"),
                        rs.getInt("tutorial_id"), id, rs.getInt("instruction_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(tutorialLinks);
    }

    @GetMapping("/files/images/{filename}")
    public ResponseEntity<Resource> image(@PathVariable String filename)
    {
        File file = new File(pathBase + "images\\" + filename);
        HttpHeaders header = makeHeader(filename);
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping("/files/vids/{filename}")
    public ResponseEntity<Resource> vid(@PathVariable String filename)
    {
        File file = new File(pathBase + "vids\\" + filename);
        HttpHeaders header = makeHeader(filename);
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping("/files/sounds/{filename}")
    public ResponseEntity<Resource> sound(@PathVariable String filename)
    {
        File file = new File(pathBase + "sounds\\" + filename);
        HttpHeaders header = makeHeader(filename);
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping("/files/narrations/{filename}")
    public ResponseEntity<Resource> narration(@PathVariable String filename)
    {
        File file = new File(pathBase + "narrations\\" + filename);
        HttpHeaders header = makeHeader(filename);
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
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
