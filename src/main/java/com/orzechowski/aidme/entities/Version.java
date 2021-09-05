package com.orzechowski.aidme.entities;

public class Version
{
    private long versionId;
    private final String text;
    private final long tutorialId;
    private final boolean delayGlobalSound;
    private final boolean hasChildren;
    private final boolean hasParent;
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
}
