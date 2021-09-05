package com.orzechowski.aidme.entities;

public class TagKeyword
{
    private long tagKeywordId;
    private final long keywordId;
    private final long tagId;

    public TagKeyword(long keywordId, long tagId)
    {
        this.keywordId = keywordId;
        this.tagId = tagId;
    }

    public TagKeyword(long tagKeywordId, long keywordId, long tagId)
    {
        this.tagKeywordId = tagKeywordId;
        this.keywordId = keywordId;
        this.tagId = tagId;
    }
}
