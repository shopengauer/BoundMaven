/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects.techdocuments.types;

import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.Time;
import com.matrix.boundmaven.entity.techobjects.techdocuments.TechDocFilesBundleEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "TECHDOC_FILE_TYPE_ENTITY")
public class TechDocFileTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TECHDOC_FILE_TYPE_ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "TECHDOC_FILE_TYPE_NAME",length = 45,unique = true)
    private String techDocFileTypeName;
    
    @Basic 
    @Column(name = "TECHDOC_FILE_TYPE_DESCRIPTION")
    private String techDocFileTypeDesc;
    
    @OneToMany(mappedBy = "techDocFileTypeEntity",fetch = FetchType.LAZY)
    private List<TechDocFilesBundleEntity> techDocFilesBundleEntities; 
    
    @Embedded
    private Time ctime;
    
     
  
    public String getTechDocFileTypeName() {
        return techDocFileTypeName;
    }

    public void setTechDocFileTypeName(String techDocFileTypeName) {
        this.techDocFileTypeName = techDocFileTypeName;
    }

    public String getTechDocFileTypeDesc() {
        return techDocFileTypeDesc;
    }

    public void setTechDocFileTypeDesc(String techDocFileTypeDesc) {
        this.techDocFileTypeDesc = techDocFileTypeDesc;
    }

    public List<TechDocFilesBundleEntity> getTechDocFilesBundleEntities() {
        return techDocFilesBundleEntities;
    }

    public void setTechDocFilesBundleEntities(List<TechDocFilesBundleEntity> techDocFilesBundleEntities) {
        this.techDocFilesBundleEntities = techDocFilesBundleEntities;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
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
        //   Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TechDocFileTypeEntity)) {
            return false;
        }
        TechDocFileTypeEntity other = (TechDocFileTypeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.techobjects.TechDocFileTypeEntity[ id=" + id + " ]";
    }
    
}
