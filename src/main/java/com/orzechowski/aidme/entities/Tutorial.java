package com.orzechowski.aidme.entities;

public class Tutorial
{
    private long tutorialId;
    private final String tutorialName;
    private final long authorId;
    private final String miniatureName;
    private final float rating;


    public Tutorial(String tutorialName, long authorId, String miniatureName, float rating)
    {
        this.tutorialName = tutorialName;
        this.authorId = authorId;
        this.miniatureName = miniatureName;
        this.rating = rating;
    }

    public Tutorial(long tutorialId, String tutorialName, long authorId, String miniatureName, float rating)
    {
        this.tutorialId = tutorialId;
        this.tutorialName = tutorialName;
        this.authorId = authorId;
        this.miniatureName = miniatureName;
        this.rating = rating;
    }
}
