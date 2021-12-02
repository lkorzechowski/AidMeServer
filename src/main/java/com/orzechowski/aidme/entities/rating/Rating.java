package com.orzechowski.aidme.entities.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating
{
    @Id
    @Column(name = "rating_id", nullable = false)
    private final long ratingId;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;
    @Column(name = "device_id")
    private final String deviceId;
    @Column(name = "rating", nullable = false)
    private final float rating;

    public Rating(long ratingId, long tutorialId, String deviceId, float rating)
    {
        this.ratingId = ratingId;
        this.tutorialId = tutorialId;
        this.deviceId = deviceId;
        this.rating = rating;
    }

    public long getRatingId()
    {
        return ratingId;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public float getRating()
    {
        return rating;
    }
}
