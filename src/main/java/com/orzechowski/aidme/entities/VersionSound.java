package com.orzechowski.aidme.entities;

public class VersionSound
{
    private long versionSoundId;
    private final long tutorialSoundId;
    private final long versionId;

    public VersionSound(long tutorialSoundId, long versionId)
    {
        this.tutorialSoundId = tutorialSoundId;
        this.versionId = versionId;
    }

    public VersionSound(long versionSoundId, long tutorialSoundId, long versionId)
    {
        this.versionSoundId = versionSoundId;
        this.tutorialSoundId = tutorialSoundId;
        this.versionId = versionId;
    }
}
