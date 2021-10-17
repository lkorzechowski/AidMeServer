package com.orzechowski.aidme.entities.versionsound;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "version_sounds")
public class VersionSound
{
    @Id
    private long versionSoundId;
    @Column(name = "tutorial_sound_id", nullable = false)
    private long tutorialSoundId;
    @Column(name = "version_id", nullable = false)
    private long versionId;

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

    public long getVersionSoundId()
    {
        return versionSoundId;
    }

    public long getTutorialSoundId()
    {
        return tutorialSoundId;
    }

    public long getVersionId()
    {
        return versionId;
    }
}
