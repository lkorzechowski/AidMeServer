package com.orzechowski.aidme.entities;

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
    private final long versionId;
    @Column(name = "instruction_position", nullable = false)
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
