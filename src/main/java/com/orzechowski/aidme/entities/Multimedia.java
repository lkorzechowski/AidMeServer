package com.orzechowski.aidme.entities;

public class Multimedia
{
    private long multimediaId;
    private final long tutorialId;
    private final int displayTime;
    private final boolean type;
    private final String fileName;
    private final boolean loop;
    private final int position;

    public Multimedia(long tutorialId, int displayTime, boolean type, String fileName, boolean loop, int position)
    {
        this.tutorialId = tutorialId;
        this.displayTime = displayTime;
        this.type = type;
        this.fileName = fileName;
        this.loop = loop;
        this.position = position;
    }

    public Multimedia(long multimediaId, long tutorialId, int displayTime, boolean type, String fileName, boolean loop,
                      int position)
    {
        this.multimediaId = multimediaId;
        this.tutorialId = tutorialId;
        this.displayTime = displayTime;
        this.type = type;
        this.fileName = fileName;
        this.loop = loop;
        this.position = position;
    }
}
