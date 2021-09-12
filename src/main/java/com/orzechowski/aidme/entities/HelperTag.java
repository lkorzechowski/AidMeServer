package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "helper_tags")
public class HelperTag
{
    @Id
    private long helperTagId;
    @Column(name = "helper_id", nullable = false)
    private final long helperId;
    @Column(name = "tag_id", nullable = false)
    private final long tagId;

    public HelperTag(long helperId, long tagId)
    {
        this.helperId = helperId;
        this.tagId = tagId;
    }

    public HelperTag(long helperTagId, long helperId, long tagId)
    {
        this.helperTagId = helperTagId;
        this.helperId = helperId;
        this.tagId = tagId;
    }
}
