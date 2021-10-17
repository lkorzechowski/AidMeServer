package com.orzechowski.aidme.entities.versioninstruction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "version_instructions")
public class VersionInstruction
{
    @Id
    private long versionInstructionId;
    @Column(name = "version_id", nullable = false)
    private long versionId;
    @Column(name = "instruction_position", nullable = false)
    private int instructionPosition;

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

    public long getVersionInstructionId()
    {
        return versionInstructionId;
    }

    public long getVersionId()
    {
        return versionId;
    }

    public int getInstructionPosition()
    {
        return instructionPosition;
    }
}
