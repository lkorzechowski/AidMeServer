package com.orzechowski.aidme.entities.tutorialsound;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial_sounds")
public class TutorialSound
{
    @Id
    private long soundId;
    @Column(name = "sound_start", nullable = false)
    private int soundStart;
    @Column(name = "sound_loop", nullable = false)
    private boolean soundLoop;
    @Column(name = "interval", nullable = false)
    private int interval;
    @Column(name = "tutorial_id", nullable = false)
    private long tutorialId;
    @Column(name = "file_name", nullable = false)
    private String fileName;

    public TutorialSound(int soundStart, boolean soundLoop, int interval, long tutorialId, String fileName)
    {
        this.soundStart = soundStart;
        this.soundLoop = soundLoop;
        this.interval = interval;
        this.tutorialId = tutorialId;
        this.fileName = fileName;
    }

    public TutorialSound(long soundId, int soundStart, boolean soundLoop, int interval, long tutorialId,
                         String fileName)
    {
        this.soundId = soundId;
        this.soundStart = soundStart;
        this.soundLoop = soundLoop;
        this.interval = interval;
        this.tutorialId = tutorialId;
        this.fileName = fileName;
    }

    public long getSoundId()
    {
        return soundId;
    }

    public long getSoundStart()
    {
        return soundStart;
    }

    public boolean isSoundLoop()
    {
        return soundLoop;
    }

    public long getInterval()
    {
        return interval;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public String getFileName()
    {
        return fileName;
    }
}
