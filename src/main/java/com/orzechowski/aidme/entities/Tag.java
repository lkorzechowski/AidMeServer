package com.orzechowski.aidme.entities;

public class Tag
{
    private long tagId;
    private final String tagName;
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
