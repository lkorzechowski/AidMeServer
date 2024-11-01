package com.orzechowski.aidme.entities.tutoriallink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial_link")
public class TutorialLink
{
    @Id
    @Column(name = "tutorial_link_id", nullable = false)
    private final long tutorialLinkId;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "origin_id", nullable = false)
    private final long originId;
    @Column(name = "instruction_number", nullable = false)
    private final int instructionNumber;

    public TutorialLink(long tutorialLinkId, long tutorialId, long originId, int instructionNumber)
    {
        this.tutorialLinkId = tutorialLinkId;
        this.tutorialId = tutorialId;
        this.originId = originId;
        this.instructionNumber = instructionNumber;
    }

    public long getTutorialLinkId()
    {
        return tutorialLinkId;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public long getOriginId()
    {
        return originId;
    }

    public int getInstructionNumber()
    {
        return instructionNumber;
    }
}
