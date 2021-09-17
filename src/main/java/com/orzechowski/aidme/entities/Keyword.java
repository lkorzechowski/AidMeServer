package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keywords")
public class Keyword
{
    @Id
    private long keywordId;
    @Column(name = "word", nullable = false)
    private final String word;

    public Keyword(String word)
    {
        this.word = word;
    }

    public Keyword(long keywordId, String word)
    {
        this.keywordId = keywordId;
        this.word = word;
    }

    public long getKeywordId()
    {
        return keywordId;
    }

    public String getWord()
    {
        return word;
    }
}
