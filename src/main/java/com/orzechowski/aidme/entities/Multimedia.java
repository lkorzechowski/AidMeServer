package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "multimedia")
public class Multimedia
{
    @Id
    private long multimediaId;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "display_time", nullable = false)
    private final int displayTime;
    @Column(name = "multimedia_type", nullable = false)
    private final boolean type;
    @Column(name = "file_name", nullable = false)
    private final String fileName;
    @Column(name = "multimedia_loop", nullable = false)
    private final boolean loop;
    @Column(name = "multimedia_position", nullable = false)
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

    public long getMultimediaId()
    {
        return multimediaId;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public int getDisplayTime()
    {
        return displayTime;
    }

    public boolean isType()
    {
        return type;
    }

    public String getFileName()
    {
        return fileName;
    }

    public boolean isLoop()
    {
        return loop;
    }

    public int getPosition()
    {
        return position;
    }
}
