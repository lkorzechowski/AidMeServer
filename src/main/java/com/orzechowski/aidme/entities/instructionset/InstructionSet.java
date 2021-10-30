package com.orzechowski.aidme.entities.instructionset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instruction_set")
public class InstructionSet
{
    @Id
    @Column(name = "instruction_set_id", nullable = false)
    private long instructionSetId;
    @Column(name = "instruction_title", nullable = false)
    private String title;
    @Column(name = "instructions", nullable = false)
    private String instructions;
    @Column(name = "instruction_duration", nullable = false)
    private int time;
    @Column(name = "tutorial_id", nullable = false)
    private long tutorialId;
    @Column(name = "instruction_position", nullable = false)
    private int position;
    @Column(name = "instruction_narration_file")
    private String narrationName;

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
