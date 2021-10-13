package com.orzechowski.aidme.entities.tutorialtag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial_tags")
public class TutorialTag
{
    @Id
    private long tutorialTagId;
    @Column(name = "tutorial_id")
    private final long tutorialId;
    @Column(name = "tag_id")
    private final long tagId;

    public TutorialTag(long tutorialId, long tagId)
    {
        this.tutorialId = tutorialId;
        this.tagId = tagId;
    }

    public TutorialTag(long tutorialTagId, long tutorialId, long tagId)
    {
        this.tutorialTagId = tutorialTagId;
        this.tutorialId = tutorialId;
        this.tagId = tagId;
    }

    public long getTutorialTagId()
    {
        return tutorialTagId;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public long getTagId()
    {
        return tagId;
    }
}
