/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.partreference;

import com.matrix.boundmaven.entity.Time;
import com.matrix.boundmaven.entity.techobjects.Device;
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
import javax.persistence.Index;
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
@Table(name = "ROWOBJECTS")
public class RowObject implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWOBJECT_ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1,max = 45)
    @Column(name = "COD", nullable = false, length = 45, unique = true)
    private String cod;
   
    @Basic
    @Size(min = 0, max = 45)
    @Column(name = "PACK")
    private String pack;
    
    @Basic
    @Size(min = 0, max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Basic
    @Size(max = 45)
    @Column(name = "VALUE")
    private String value;
    
    @Basic
    @Size(max = 45)
    @Column(name = "VOLTAGE")
    private String voltage;
    
    @Basic
    @Size(max = 45)
    @Column(name = "CURRENT")
    private String current;
    
    @Basic
    @Size(max = 45)
    @Column(name = "POWER")
    private String power;
    
    @Basic
    @Size(max = 45)
    @Column(name = "TOLERANCE")
    private String tolerance;
    
    @Basic
    @Size(max = 45)
    @Column(name = "MANUFACTURE")
    private String manufacture;
    
    @Basic
    @Size(max = 45)
    @Column(name = "ROHS")
    private String rohs;
           
           
    @Embedded
    private Time ctime;
   
     
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
