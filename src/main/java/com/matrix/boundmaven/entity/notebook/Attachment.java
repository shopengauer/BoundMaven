/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.notebook;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Embeddable
public class Attachment implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "FILENAME", nullable = false)
    private String fileName;
    
    @Lob
    @Basic(optional = false,fetch = FetchType.LAZY)
    @NotNull
    @Column(name = "FILE", nullable = false,columnDefinition = "MEDIUMBLOB")
    private byte[] file;
        
    @Basic
    @Size(max = 255)
    @Column(name = "FILE_DESCRIPTION")
    private String fileDescription;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }
  
    
    
}
