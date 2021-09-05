package com.orzechowski.aidme.entities;

public class InstructionSet
{
    private long instructionSetId;
    private final String title;
    private final String instructions;
    private final int time;
    private final long tutorialId;
    private final int position;
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
}
