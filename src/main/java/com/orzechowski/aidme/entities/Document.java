package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document
{
    @Id
    private long documentId;
    @Column(name = "file_name", nullable = false)
    private final String fileName;
    @Column(name = "description")
    private String description;
    @Column(name = "helper_id", nullable = false)
    private final int helperId;

    public Document(String fileName, String description, int helperId)
    {
        this.fileName = fileName;
        this.description = description;
        this.helperId = helperId;
    }

    public Document(long documentId, String fileName, String description, int helperId)
    {
        this.documentId = documentId;
        this.fileName = fileName;
        this.description = description;
        this.helperId = helperId;
    }

    public Document(String fileName, int helperId)
    {
        this.fileName = fileName;
        this.helperId = helperId;
    }

    public Document(long documentId, String fileName, int helperId)
    {
        this.documentId = documentId;
        this.fileName = fileName;
        this.helperId = helperId;
    }
}
