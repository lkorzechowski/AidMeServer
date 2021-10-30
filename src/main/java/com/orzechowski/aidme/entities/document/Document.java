package com.orzechowski.aidme.entities.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document
{
    @Id
    @Column(name = "document_id", nullable = false)
    private long documentId;
    @Column(name = "document_file_name", nullable = false)
    private String fileName;
    @Column(name = "document_description")
    private String description;
    @Column(name = "helper_id", nullable = false)
    private long helperId;

    public Document(long documentId, String fileName, String description, long helperId)
    {
        this.documentId = documentId;
        this.fileName = fileName;
        this.description = description;
        this.helperId = helperId;
    }
}
