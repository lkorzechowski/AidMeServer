package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    private final static String url = "jdbc:postgresql://localhost:5432/AidMe";
    private final static String user = "spring";
    private final static String password = "malina1213141516171819";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String home()
    {
        return "";
    }

    @GetMapping("/helpers")
    public ResponseEntity<List<Helper>> helpers()
    {
        List<Helper> helpers = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM helpers");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                helpers.add(new Helper(rs.getInt("helper_id"), rs.getString("helper_name"),
                        rs.getString("helper_surname"), rs.getString("helper_title"),
                        rs.getString("helper_profession")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(helpers);
    }

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
                    connection.prepareStatement("SELECT * FROM instruction_sets WHERE tutorial_id = "+ id);
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
                versionInstructions.add(new VersionInstruction(rs.getInt("version_instruction_id"),
                        rs.getInt("version_id"), rs.getInt("instruction_position")));
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

    @GetMapping("/versionsounds/{id}")
    public ResponseEntity<List<VersionSound>> versionSounds(@PathVariable long id)
    {
        List<VersionSound> versionSounds = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM version_sounds WHERE version_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                versionSounds.add(new VersionSound(rs.getInt("version_sound_id"),
                        rs.getInt("version_id"), rs.getInt("sound_id")));
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

    @GetMapping("/tutoriallinks/{id}")
    public ResponseEntity<List<TutorialLink>> tutoriallinks(@PathVariable long id)
    {
        List<TutorialLink> tutorialLinks = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM tutorial_links WHERE tutorial_id = " + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                tutorialLinks.add(new TutorialLink(rs.getInt("tutorial_link_id"),
                        rs.getInt("tutorial_id"), rs.getInt("origin_id"),
                        rs.getInt("instruction_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(tutorialLinks);
    }
}
