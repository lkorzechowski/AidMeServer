package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/helpers")
    public List<Helper> helpers()
    {
        return jdbcTemplate.query("SELECT * FROM helpers", BeanPropertyRowMapper.newInstance(Helper.class));
    }

    @PostMapping("/tutorials")
    public List<Tutorial> tutorials()
    {
        return jdbcTemplate.query("SELECT * FROM tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
    }

    @PostMapping("/categories")
    public List<Category> categories()
    {
        return jdbcTemplate.query("SELECT * FROM categories", BeanPropertyRowMapper.newInstance(Category.class));
    }

    @PostMapping("/tags")
    public List<Tag> tags()
    {
        return jdbcTemplate.query("SELECT * FROM tags", BeanPropertyRowMapper.newInstance(Tag.class));
    }

    @PostMapping("/keywords")
    public List<Keyword> keywords()
    {
        return jdbcTemplate.query("SELECT * FROM keywords", BeanPropertyRowMapper.newInstance(Keyword.class));
    }
}
