package com.orzechowski.aidme.entities.version;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "version")
public class Version
{
    @Id
    @Column(name = "version_id", nullable = false)
    private long versionId;
    @Column(name = "version_text", nullable = false)
    private String text;
    @Column(name = "tutorial_id", nullable = false)
    private long tutorialId;
    @Column(name = "version_sound_delay", nullable = false)
    private boolean delayGlobalSound;
    @Column(name = "version_has_children", nullable = false)
    private boolean hasChildren;
    @Column(name = "version_has_parent", nullable = false)
    private boolean hasParent;
    @Column(name = "parent_version_id")
    private long parentVersionId;

    public Version(long versionId, String text, long tutorialId, boolean delayGlobalSound, boolean hasChildren,
                   boolean hasParent)
    {
        this.versionId = versionId;
        this.text = text;
        this.tutorialId = tutorialId;
        this.delayGlobalSound = delayGlobalSound;
        this.hasChildren = hasChildren;
        this.hasParent = hasParent;
    }

    public Version(long versionId, String text, long tutorialId, boolean delayGlobalSound, boolean hasChildren,
                   boolean hasParent, long parentVersionId)
    {
        this.versionId = versionId;
        this.text = text;
        this.tutorialId = tutorialId;
        this.delayGlobalSound = delayGlobalSound;
        this.hasChildren = hasChildren;
        this.hasParent = hasParent;
        this.parentVersionId = parentVersionId;
    }

    public long getVersionId()
    {
        return versionId;
    }

    public String getText()
    {
        return text;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public boolean isDelayGlobalSound()
    {
        return delayGlobalSound;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public boolean isHasParent()
    {
        return hasParent;
    }

    public long getParentVersionId()
    {
        return parentVersionId;
    }
}
