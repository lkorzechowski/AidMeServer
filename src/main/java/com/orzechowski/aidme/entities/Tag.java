package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag
{
    @Id
    private long tagId;
    @Column(name = "tag_name", nullable = false)
    private final String tagName;
    @Column(name = "tag_level", nullable = false)
    private final int tagLevel;

    public Tag(String tagName, int tagLevel)
    {
        this.tagName = tagName;
        this.tagLevel = tagLevel;
    }

    public Tag(long tagId, String tagName, int tagLevel)
    {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagLevel = tagLevel;
    }
}
