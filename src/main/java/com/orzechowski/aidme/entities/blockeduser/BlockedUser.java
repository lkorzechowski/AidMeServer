package com.orzechowski.aidme.entities.blockeduser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blocked_users")
public class BlockedUser
{
    @Id
    private long blockedUserId;
    @Column(name = "phone_number", nullable = false)
    private final String phoneNumber;

    public BlockedUser(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public BlockedUser(long blockedUserId, String phoneNumber)
    {
        this.blockedUserId = blockedUserId;
        this.phoneNumber = phoneNumber;
    }

    public long getBlockedUserId()
    {
        return blockedUserId;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
