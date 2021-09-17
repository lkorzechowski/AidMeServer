package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instruction_sets")
public class InstructionSet
{
    @Id
    private long instructionSetId;
    @Column(name = "title", nullable = false)
    private final String title;
    @Column(name = "instructions", nullable = false)
    private final String instructions;
    @Column(name = "time", nullable = false)
    private final int time;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "position", nullable = false)
    private final int position;
    @Column(name = "narration_name")
    private String narrationName;

    public InstructionSet(String title, String instructions, int time, long tutorialId, int position,
                          String narrationName)
    {
        this.title = title;
        this.instructions = instructions;
        this.time = time;
        this.tutorialId = tutorialId;
        this.position = position;
        this.narrationName = narrationName;
    }

    public InstructionSet(String title, String instructions, int time, long tutorialId, int position)
    {
        this.title = title;
        this.instructions = instructions;
        this.time = time;
        this.tutorialId = tutorialId;
        this.position = position;
    }

    public InstructionSet(long instructionSetId, String title, String instructions, int time, long tutorialId,
                          int position)
    {
        this.instructionSetId = instructionSetId;
        this.title = title;
        this.instructions = instructions;
        this.time = time;
        this.tutorialId = tutorialId;
        this.position = position;
    }

    public InstructionSet(long instructionSetId, String title, String instructions, int time, long tutorialId,
                          int position, String narrationName)
    {
        this.instructionSetId = instructionSetId;
        this.title = title;
        this.instructions = instructions;
        this.time = time;
        this.tutorialId = tutorialId;
        this.position = position;
        this.narrationName = narrationName;
    }

    public long getInstructionSetId()
    {
        return instructionSetId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public int getTime()
    {
        return time;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public int getPosition()
    {
        return position;
    }

    public String getNarrationName()
    {
        return narrationName;
    }
}
