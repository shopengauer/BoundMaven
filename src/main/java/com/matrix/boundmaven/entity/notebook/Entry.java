/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.notebook;

import com.matrix.boundmaven.entity.Time;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "ENTRY")
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTRY_ID",nullable = false)
    private Long id;
    
    @Basic 
    @Column(name = "ENTRY_NAME",columnDefinition = "VARCHAR(128)")
    private String entryName;
    
    @Basic
    @Column(name = "ENTRY_TEXT",columnDefinition = "VARCHAR(1024)")
    private String entryText;
    
    @Basic
    @Column(name = "ENTRY_TAG")
    private String entryTag;
    
    @Basic(optional = false)
    @Column(name = "ENTRY_SIZE",columnDefinition = "VARCHAR(1024) NOT NULL")   
    private String entrySize;
    
    
    @ElementCollection
    @CollectionTable(name = "ATTACHMENTS",joinColumns = @JoinColumn(name = "ATTACHMENT_ID"))
    private List<Attachment> attachments;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_NOTEBOOK_ID",referencedColumnName = "NOTEBOOK_ID")
    private Notebook notebookName;
    
    @Embedded
    private Time ctime;    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public String getEntryTag() {
        return entryTag;
    }

    public void setEntryTag(String entryTag) {
        this.entryTag = entryTag;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Notebook getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(Notebook notebookName) {
        this.notebookName = notebookName;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //  Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.notebook.Entry[ id=" + id + " ]";
    }
    
}
