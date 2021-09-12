package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
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
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM helpers",
                BeanPropertyRowMapper.newInstance(Helper.class)), HttpStatus.OK);
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> tutorials()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM tutorials",
                BeanPropertyRowMapper.newInstance(Tutorial.class)), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM categories",
                BeanPropertyRowMapper.newInstance(Category.class)), HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> tags()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM tags",
                BeanPropertyRowMapper.newInstance(Tag.class)), HttpStatus.OK);
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<Keyword>> keywords()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM keywords",
                BeanPropertyRowMapper.newInstance(Keyword.class)), HttpStatus.OK);
    }

    @GetMapping("/tagkeywords")
    public ResponseEntity<List<TagKeyword>> tagKeywords()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM tag_keywords",
                BeanPropertyRowMapper.newInstance(TagKeyword.class)), HttpStatus.OK);
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<BlockedUser>> blockedUsers()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM blocked_users",
                BeanPropertyRowMapper.newInstance(BlockedUser.class)), HttpStatus.OK);
    }

    @GetMapping("/categorytags")
    public ResponseEntity<List<CategoryTag>> categoryTags()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM category_tags",
                BeanPropertyRowMapper.newInstance(CategoryTag.class)), HttpStatus.OK);
    }

    @GetMapping("/helpertags")
    public ResponseEntity<List<HelperTag>> helperTags()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM helper_tags",
                BeanPropertyRowMapper.newInstance(HelperTag.class)), HttpStatus.OK);
    }

    @GetMapping("/tutorialtags")
    public ResponseEntity<List<TutorialTag>> tutorialTags()
    {
        return new ResponseEntity<>(jdbcTemplate.query("SELECT * FROM tutorial_tags",
                BeanPropertyRowMapper.newInstance(TutorialTag.class)), HttpStatus.OK);
    }

    @GetMapping("/tutorialinstructions")
    public ResponseEntity<List<InstructionSet>> tutorialInstructions(@PathVariable long tutorialId)
    {
        return new ResponseEntity<>(jdbcTemplate
                .query("SELECT * FROM instruction_sets WHERE tutorial_id = "+ tutorialId,
                        BeanPropertyRowMapper.newInstance(InstructionSet.class)), HttpStatus.OK);
    }

    @GetMapping("/tutorialversions")
    public ResponseEntity<List<Version>> tutorialVersions(@PathVariable long tutorialId)
    {
        return new ResponseEntity<>(jdbcTemplate
                .query("SELECT * FROM versions WHERE tutorial_id = " + tutorialId,
                        BeanPropertyRowMapper.newInstance(Version.class)), HttpStatus.OK);
    }
}
