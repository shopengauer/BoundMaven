/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */

@Embeddable
@Access(AccessType.FIELD)
public class TechDocFilesBundle implements Serializable {
   
    @Enumerated(EnumType.STRING)
    private TechDocFileType techDocFileType; 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_FILENAME", length = 45,nullable = false)
    private String techDocFileName; 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_FILECOD", length = 45,nullable = false)
    private String techDocFileCod; 
     
    @Basic
    @Column(name = "TECHDOC_FILENAME_DESCRIPTION")
    private String techDocFileNameDesc;
      
    @Lob
    @Basic(optional = true,fetch = FetchType.LAZY)
    @Column(name = "TECHDOC_FILE")
    private Byte[] techDocFile; 

    public TechDocFileType getTechDocFileType() {
        return techDocFileType;
    }

    public void setTechDocFileType(TechDocFileType techDocFileType) {
        this.techDocFileType = techDocFileType;
    }

    public String getTechDocFileName() {
        return techDocFileName;
    }

    public void setTechDocFileName(String techDocFileName) {
        this.techDocFileName = techDocFileName;
    }

    public String getTechDocFileCod() {
        return techDocFileCod;
    }

    public void setTechDocFileCod(String techDocFileCod) {
        this.techDocFileCod = techDocFileCod;
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






}
