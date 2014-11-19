/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.partreference;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "ROWOBJECTS")
public class RowObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWOBJECT_ID")
    private Long id;

    
    private String cod;
    private String pack;
    private String description;
    private String value;
    private String voltage;
    private String current;
    private String power;
    private String tolerance;
    private String manufacture;
    private String rohs;
           
           
    
    
//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(name = "ROWOBJECT_DOCFILES", joinColumns = @JoinColumn(name = "ROWOBJECT_DOCFILE_ID"))
//    private List<TechDocs> rowObjectDocFiles;
//    
    
   // @ManyToOne
   // @JoinColumn(name = "FK_BOMFILE_ID", referencedColumnName = "BOMFILE_ID")
   // private BomFile bomFile;
     
    @OneToMany(mappedBy = "rowObject")
    private List<RowObjectPartRef> rowObjectPartRefs;
    
    
    
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
        if (!(object instanceof RowObject)) {
            return false;
        }
        RowObject other = (RowObject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.RowObject[ id=" + id + " ]";
    }
    
}
