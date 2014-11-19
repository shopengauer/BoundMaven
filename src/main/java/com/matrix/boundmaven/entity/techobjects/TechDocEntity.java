/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TECHDOC_ENTITY_ID")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_TECHDOC_TYPE_ID",referencedColumnName = "TECHDOC_TYPE_ID")
    private TechDocType techDocType;
    
   
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Size()
    private String techDocName;
       
    private String techDocCod;
    
    private String techDocFileName;    
    
    private String techDocDescription;
    
    
    
    @Lob
    @Column(name = "TECHDOC_FILE",length = 20947787)
    private Byte[] techDocFile; 
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_ID",referencedColumnName = "DEVICE_ID")
    private Device device;
    
   
    
    
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
