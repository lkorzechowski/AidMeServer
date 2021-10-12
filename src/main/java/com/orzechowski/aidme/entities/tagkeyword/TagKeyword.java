package com.orzechowski.aidme.entities.tagkeyword;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_keywords")
public class TagKeyword
{
    @Id
    private long tagKeywordId;
    @Column(name = "keyword_id", nullable = false)
    private final long keywordId;
    @Column(name = "tag_id", nullable = false)
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

    public long getTagKeywordId()
    {
        return tagKeywordId;
    }

    public long getKeywordId()
    {
        return keywordId;
    }

    public long getTagId()
    {
        return tagId;
    }
}
