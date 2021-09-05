package com.orzechowski.aidme.entities;

public class CategoryTag
{
    private long categoryTagId;
    private final long categoryId;
    private final long tagId;

    public CategoryTag(long categoryId, long tagId)
    {
        this.categoryId = categoryId;
        this.tagId = tagId;
    }

    public CategoryTag(long categoryTagId, long categoryId, long tagId)
    {
        this.categoryTagId = categoryTagId;
        this.categoryId = categoryId;
        this.tagId = tagId;
    }
}
