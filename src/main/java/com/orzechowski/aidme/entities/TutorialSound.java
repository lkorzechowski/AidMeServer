package com.orzechowski.aidme.entities;

public class TutorialSound
{
    private long soundId;
    private final long soundStart;
    private final boolean soundLoop;
    private final long interval;
    private final long tutorialId;
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
