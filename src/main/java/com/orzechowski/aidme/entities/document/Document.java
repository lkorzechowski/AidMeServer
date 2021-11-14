package com.orzechowski.aidme.entities.document;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("documentId")
    private long documentId;
    @Column(name = "document_file_name", nullable = false)
    @JsonProperty("filename")
    private String fileName;
    @Column(name = "document_description")
    @JsonProperty("description")
    private String description;
    @Column(name = "helper_id", nullable = false)
    @JsonProperty("helperId")
    private long helperId;

    public Document(long documentId, String fileName, String description, long helperId)
    {
        this.documentId = documentId;
        this.fileName = fileName;
        this.description = description;
        this.helperId = helperId;
    }

    public long getDocumentId()
    {
        return documentId;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getDescription()
    {
        return description;
    }

    public long getHelperId()
    {
        return helperId;
    }

    public void setDocumentId(long documentId)
    {
        this.documentId = documentId;
    }
}
