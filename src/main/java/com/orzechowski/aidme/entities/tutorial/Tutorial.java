package com.orzechowski.aidme.entities.tutorial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial
{
    @Id
    private long tutorialId;
    @Column(name = "tutorial_name", nullable = false)
    private final String tutorialName;
    @Column(name = "author_id", nullable = false)
    private final long authorId;
    @Column(name = "miniature_name", nullable = false)
    private final String miniatureName;
    @Column(name = "rating", nullable = false)
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

    public long getTutorialId()
    {
        return tutorialId;
    }

    public String getTutorialName()
    {
        return tutorialName;
    }

    public long getAuthorId()
    {
        return authorId;
    }

    public String getMiniatureName()
    {
        return miniatureName;
    }

    public float getRating()
    {
        return rating;
    }
}
