package com.orzechowski.aidme.entities;

public class VersionInstruction
{
    private long versionInstructionId;
    private final long versionId;
    private final int instructionPosition;

    public VersionInstruction(long versionId, int instructionPosition)
    {
        this.versionId = versionId;
        this.instructionPosition = instructionPosition;
    }

    public VersionInstruction(long versionInstructionId, long versionId, int instructionPosition)
    {
        this.versionInstructionId = versionInstructionId;
        this.versionId = versionId;
        this.instructionPosition = instructionPosition;
    }
}
