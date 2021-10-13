package com.orzechowski.aidme.entities.version;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "versions")
public class Version
{
    @Id
    private long versionId;
    @Column(name = "text", nullable = false)
    private final String text;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "delay_global_sound", nullable = false)
    private final boolean delayGlobalSound;
    @Column(name = "has_children", nullable = false)
    private final boolean hasChildren;
    @Column(name = "has_parent", nullable = false)
    private final boolean hasParent;
    @Column(name = "parent_version_id")
    private long parentVersionId;

    public Version(String text, long tutorialId, boolean delayGlobalSound, boolean hasChildren, boolean hasParent)
    {
        this.text = text;
        this.tutorialId = tutorialId;
        this.delayGlobalSound = delayGlobalSound;
        this.hasChildren = hasChildren;
        this.hasParent = hasParent;
    }

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

    public Version(String text, long tutorialId, boolean delayGlobalSound, boolean hasChildren, boolean hasParent,
                   long parentVersionId)
    {
        this.text = text;
        this.tutorialId = tutorialId;
        this.delayGlobalSound = delayGlobalSound;
        this.hasChildren = hasChildren;
        this.hasParent = hasParent;
        this.parentVersionId = parentVersionId;
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
