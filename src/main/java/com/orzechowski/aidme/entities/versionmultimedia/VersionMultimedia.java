package com.orzechowski.aidme.entities.versionmultimedia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "version_multimedia")
public class VersionMultimedia
{
    @Id
    @Column(name = "version_multimedia_id", nullable = false)
    private final long versionMultimediaId;
    @Column(name = "multimedia_id", nullable = false)
    private final long multimediaId;
    @Column(name = "version_id", nullable = false)
    private final long versionId;

    public VersionMultimedia(long versionMultimediaId, long multimediaId, long versionId)
    {
        this.versionMultimediaId = versionMultimediaId;
        this.multimediaId = multimediaId;
        this.versionId = versionId;
    }

    public long getVersionMultimediaId()
    {
        return versionMultimediaId;
    }

    public long getMultimediaId()
    {
        return multimediaId;
    }

    public long getVersionId()
    {
        return versionId;
    }
}
