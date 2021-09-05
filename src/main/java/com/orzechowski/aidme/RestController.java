package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    @GetMapping("/")
    public String home()
    {
        return "";
    }

    @PostMapping("/helpers")
    public List<Helper> helpers()
    {
        List<Helper> helpers = null;
        return helpers;
    }

    @PostMapping("/tutorials")
    public List<Tutorial> tutorials()
    {
        List<Tutorial> tutorials = null;
        return tutorials;
    }

    @PostMapping("/categories")
    public List<Category> categories()
    {
        List<Category> categories = null;
        return categories;
    }

    @PostMapping("/tags")
    public List<Tag> tags()
    {
        List<Tag> tags = null;
        return tags;
    }

    @PostMapping("/keywords")
    public List<Keyword> keywords()
    {
        List<Keyword> keywords = null;
        return keywords;
    }
}
