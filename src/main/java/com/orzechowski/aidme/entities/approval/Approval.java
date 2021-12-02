package com.orzechowski.aidme.entities.approval;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approval")
public class Approval
{
    @Id
    @Column(name = "approval_id", nullable = false)
    private final long approvalId;
    @Column(name = "approver_email", nullable = false)
    private final String approverEmail;
    @Column(name = "approval_decision", nullable = false)
    private final boolean approvalDecision;
    @Column(name = "tutorial_id", nullable = false)
    private final long tutorialId;

    public Approval(long approvalId, String approverEmail, boolean approvalDecision, long tutorialId)
    {
        this.approvalId = approvalId;
        this.approverEmail = approverEmail;
        this.approvalDecision = approvalDecision;
        this.tutorialId = tutorialId;
    }

    public long getApprovalId()
    {
        return approvalId;
    }

    public String getApproverEmail()
    {
        return approverEmail;
    }

    public boolean isApprovalDecision()
    {
        return approvalDecision;
    }

    public long getTutorialId()
    {
        return tutorialId;
    }
}
