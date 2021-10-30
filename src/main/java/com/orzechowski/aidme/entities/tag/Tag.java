package com.orzechowski.aidme.entities.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag
{
    @Id
    @Column(name = "tag_id", nullable = false)
    private long tagId;
    @Column(name = "tag_name", nullable = false)
    private String tagName;
    @Column(name = "tag_level", nullable = false)
    private int tagLevel;

    public Tag(long tagId, String tagName, int tagLevel)
    {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagLevel = tagLevel;
    }

    public long getTagId()
    {
        return tagId;
    }

    public String getTagName()
    {
        return tagName;
    }

    public int getTagLevel()
    {
        return tagLevel;
    }
}
