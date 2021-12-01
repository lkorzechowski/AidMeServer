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
    @Column(name = "blocked_number", nullable = false)
    private final String number;
    @Column(name = "reported_by")
    private final String reportedBy;

    public BlockedUser(String number, String reportedBy)
    {
        this.number = number;
        this.reportedBy = reportedBy;
    }

    public String getNumber()
    {
        return number;
    }

    public String getReportedBy()
    {
        return reportedBy;
    }
}
