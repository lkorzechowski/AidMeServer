package com.orzechowski.aidme.entities.blockeduser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blocked_user")
public class BlockedUser
{
    @Id
    @Column(name = "blocked_phone_number", nullable = false)
    private String phoneNumber;

    public BlockedUser(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
