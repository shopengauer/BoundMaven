/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects;

import com.matrix.boundmaven.entity.Time;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "TECHDOC_FILES_BUNDLE_ENTITY")
public class TechDocFilesBundleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TECHDOC_FILES_BUNDLE_ENTITY_ID")
    private Long id;

  
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_FILENAME",length = 45,nullable = false)
    private String techDocFileName; 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_FILECOD",length = 45,nullable = false)
    private String techDocFileCod; 
    
        
    @Basic 
    @Column(name = "TECHDOC_FILENAME_DESCRIPTION")
    private String techDocFileNameDesc;
   
     
    @Lob
    @Basic(optional = true,fetch = FetchType.LAZY)
    @Column(name = "TECHDOC_FILE")
    private Byte[] techDocFile; 
    
    @Embedded
    private Time ctime;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_TECHDOC_FILE_TYPE_ID", referencedColumnName = "TECHDOC_FILE_TYPE_ID")
    private TechDocFileTypeEntity techDocFileTypeEntity;
    
    @ManyToOne(optional = false)// TRUE ?????
    @JoinColumn(name = "FK_TECHDOC_ENTITY_ID", referencedColumnName = "TECHDOC_ENTITY_ID")
    private TechDocEntity techDocEntity;

    
    
    
    public String getTechDocFileName() {
        return techDocFileName;
    }

    public void setTechDocFileName(String techDocFileName) {
        this.techDocFileName = techDocFileName;
    }

    public String getTechDocFileNameDesc() {
        return techDocFileNameDesc;
    }

    public void setTechDocFileNameDesc(String techDocFileNameDesc) {
        this.techDocFileNameDesc = techDocFileNameDesc;
    }

    public Byte[] getTechDocFile() {
        return techDocFile;
    }

    public void setTechDocFile(Byte[] techDocFile) {
        this.techDocFile = techDocFile;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

    public TechDocFileTypeEntity getTechDocFileTypeEntity() {
        return techDocFileTypeEntity;
    }

    public void setTechDocFileTypeEntity(TechDocFileTypeEntity techDocFileTypeEntity) {
        this.techDocFileTypeEntity = techDocFileTypeEntity;
    }

    public TechDocEntity getTechDocEntity() {
        return techDocEntity;
    }

    public void setTechDocEntity(TechDocEntity techDocEntity) {
        this.techDocEntity = techDocEntity;
    }
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TechDocFilesBundleEntity)) {
            return false;
        }
        TechDocFilesBundleEntity other = (TechDocFilesBundleEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.techobjects.TechDocFilesBundleEntity[ id=" + id + " ]";
    }
    
}
