package com.orzechowski.aidme.entities;

public class Keyword
{
    private long keywordId;
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
}
