package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category_tags")
public class CategoryTag
{
    @Id
    private long categoryTagId;
    @Column(name = "category_id", nullable = false)
    private final long categoryId;
    @Column(name = "tag_id", nullable = false)
    private final long tagId;

    public CategoryTag(long categoryTagId, long categoryId, long tagId)
    {
        this.categoryTagId = categoryTagId;
        this.categoryId = categoryId;
        this.tagId = tagId;
    }

    public CategoryTag(long categoryId, long tagId)
    {
        this.categoryId = categoryId;
        this.tagId = tagId;
    }
}
