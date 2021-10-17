package com.orzechowski.aidme.entities.multimedia;

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
    private long tutorialId;
    @Column(name = "display_time", nullable = false)
    private int displayTime;
    @Column(name = "multimedia_type", nullable = false)
    private boolean type;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "multimedia_loop", nullable = false)
    private boolean loop;
    @Column(name = "multimedia_position", nullable = false)
    private int position;

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
