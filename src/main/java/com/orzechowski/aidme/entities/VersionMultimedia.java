package com.orzechowski.aidme.entities;

public class VersionMultimedia
{
    private long versionMultimediaId;
    private final long multimediaId;
    private final long versionId;

    public VersionMultimedia(long multimediaId, long versionId)
    {
        this.multimediaId = multimediaId;
        this.versionId = versionId;
    }

    public VersionMultimedia(long versionMultimediaId, long multimediaId, long versionId)
    {
        this.versionMultimediaId = versionMultimediaId;
        this.multimediaId = multimediaId;
        this.versionId = versionId;
    }
}
