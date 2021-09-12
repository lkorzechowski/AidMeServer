package com.orzechowski.aidme.entities;

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
    private final long soundStart;
    @Column(name = "sound_loop", nullable = false)
    private final boolean soundLoop;
    @Column(name = "interval", nullable = false)
    private final long interval;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "file_name", nullable = false)
    private final String fileName;

    public TutorialSound(long soundStart, boolean soundLoop, long interval, long tutorialId, String fileName)
    {
        this.soundStart = soundStart;
        this.soundLoop = soundLoop;
        this.interval = interval;
        this.tutorialId = tutorialId;
        this.fileName = fileName;
    }

    public TutorialSound(long soundId, long soundStart, boolean soundLoop, long interval, long tutorialId,
                         String fileName)
    {
        this.soundId = soundId;
        this.soundStart = soundStart;
        this.soundLoop = soundLoop;
        this.interval = interval;
        this.tutorialId = tutorialId;
        this.fileName = fileName;
    }
}
