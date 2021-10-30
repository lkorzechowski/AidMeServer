package com.orzechowski.aidme.entities.keyword;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class Keyword
{
    @Id
    @Column(name = "keyword_id", nullable = false)
    private long keywordId;
    @Column(name = "keyword", nullable = false)
    private String keyword;

    public Keyword(long keywordId, String word)
    {
        this.keywordId = keywordId;
        this.keyword = word;
    }

    public long getKeywordId()
    {
        return keywordId;
    }

    public String getKeyword()
    {
        return keyword;
    }
}
