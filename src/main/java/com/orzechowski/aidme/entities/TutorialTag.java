package com.orzechowski.aidme.entities;

public class TutorialTag
{
    private long tutorialTagId;
    private final long tutorialId;
    private final long tagId;

    public TutorialTag(long tutorialId, long tagId)
    {
        this.tutorialId = tutorialId;
        this.tagId = tagId;
    }

    public TutorialTag(long tutorialTagId, long tutorialId, long tagId)
    {
        this.tutorialTagId = tutorialTagId;
        this.tutorialId = tutorialId;
        this.tagId = tagId;
    }
}
