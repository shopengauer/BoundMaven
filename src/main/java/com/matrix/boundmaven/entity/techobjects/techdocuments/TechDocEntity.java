/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects.techdocuments;

import com.matrix.boundmaven.entity.techobjects.techdocuments.types.TechDocType;
import com.matrix.boundmaven.entity.techobjects.devices.Device;
import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.Time;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "TECHDOC_ENTITY")
public class TechDocEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TECHDOC_ENTITY_ID")
    private Long id;
    
    @Basic(optional = false)
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_NAME", nullable = false)
    private String techDocName;
    
    @Basic(optional = false)
    @Column(name = "TECHDOC_COD",unique = true)
    private String techDocCod;
    
    @Basic(optional = true) 
    @Column(name = "TECHDOC_DESCRIPTION",length = 255)
    private String techDocDescription;
    
   
   ////////////////// techdoc files bundle(extra bundle exlusive for current device) ////////////////////////////// 
    
                           @ElementCollection(fetch = FetchType.LAZY)
                           @CollectionTable(name = "TECHDOC_FILES_BUNDLE", joinColumns = @JoinColumn(name = "TECHDOC_FILES_ID"))
                           private List<TechDocFilesBundle> techDocFilesBundle;
    
    /////////////////////////////////////////////////////////////////////
       
    ////////////////// tech doc files reusable //////////////////////////
   
    @OneToMany(mappedBy = "techDocEntity")
    private List<TechDocFilesBundleEntity> techDocFilesBundleEntities;
   
   
   /////////////////////////////////////////////////////////////////////
    
    
    // For example DAR or KD or BOM
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_TECHDOC_TYPE_ID",referencedColumnName = "TECHDOC_TYPE_ID")
    private TechDocType techDocType;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_ID",referencedColumnName = "DEVICE_ID")
    private Device device;
     
    
    @Embedded
    private Time ctime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

       
    public String getTechDocName() {
        return techDocName;
    }

    public void setTechDocName(String techDocName) {
        this.techDocName = techDocName;
    }

    public String getTechDocCod() {
        return techDocCod;
    }

    public void setTechDocCod(String techDocCod) {
        this.techDocCod = techDocCod;
    }

    public String getTechDocDescription() {
        return techDocDescription;
    }

    public void setTechDocDescription(String techDocDescription) {
        this.techDocDescription = techDocDescription;
    }

    public List<TechDocFilesBundle> getTechDocFilesBundle() {
        return techDocFilesBundle;
    }

    public void setTechDocFilesBundle(List<TechDocFilesBundle> techDocFilesBundle) {
        this.techDocFilesBundle = techDocFilesBundle;
    }

    public List<TechDocFilesBundleEntity> getTechDocFilesBundleEntities() {
        return techDocFilesBundleEntities;
    }

    public void setTechDocFilesBundleEntities(List<TechDocFilesBundleEntity> techDocFilesBundleEntities) {
        this.techDocFilesBundleEntities = techDocFilesBundleEntities;
    }

    public TechDocType getTechDocType() {
        return techDocType;
    }

    public void setTechDocType(TechDocType techDocType) {
        this.techDocType = techDocType;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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
        if (!(object instanceof TechDocEntity)) {
            return false;
        }
        TechDocEntity other = (TechDocEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.techobjects.TechDocEntity[ id=" + id + " ]";
    }
    
}
