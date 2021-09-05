package com.orzechowski.aidme.entities;

public class TutorialLink
{
    private long tutorialLinkId;
    private final long tutorialId;
    private final long originId;
    private final int instructionNumber;

    public TutorialLink(long tutorialId, long originId, int instructionNumber)
    {
        this.tutorialId = tutorialId;
        this.originId = originId;
        this.instructionNumber = instructionNumber;
    }

    public TutorialLink(long tutorialLinkId, long tutorialId, long originId, int instructionNumber)
    {
        this.tutorialLinkId = tutorialLinkId;
        this.tutorialId = tutorialId;
        this.originId = originId;
        this.instructionNumber = instructionNumber;
    }
}
