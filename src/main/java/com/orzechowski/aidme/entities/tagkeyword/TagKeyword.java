package com.orzechowski.aidme.entities.tagkeyword;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_keyword")
public class TagKeyword
{
    @Id
    @Column(name = "tag_keyword_id", nullable = false)
    private long tagKeywordId;
    @Column(name = "keyword_id", nullable = false)
    private long keywordId;
    @Column(name = "tag_id", nullable = false)
    private long tagId;

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
