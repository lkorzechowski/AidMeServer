package com.orzechowski.aidme.entities.tutorial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial")
public class Tutorial
{
    @Id
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "tutorial_name", nullable = false)
    private final String tutorialName;
    @Column(name = "author_id", nullable = false)
    private final long authorId;
    @Column(name = "tutorial_miniature_file", nullable = false)
    private final String miniatureName;
    @Column(name = "tutorial_rating", nullable = false)
    private final float rating;

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
