package com.orzechowski.aidme.entities;

public class HelperTag
{
    private long helperTagId;
    private final long helperId;
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
