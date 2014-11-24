/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.partreference;

import com.matrix.boundmaven.entity.techobjects.Device;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Entity
public class RowObjectPartRef implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWOBJECT_PART_REFERENCE_ID")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 255)
    @Column(name = "PART_REFERENCE_NAME")
    private String partReferenceName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_ROWOBJECT_ID", referencedColumnName = "ROWOBJECT_ID")
    private RowObject rowObject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_ID", referencedColumnName = "DEVICE_ID")
    private Device device;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public String getPartReferenceName() {
        return partReferenceName;
    }

    public void setPartReferenceName(String partReferenceName) {
        this.partReferenceName = partReferenceName;
    }

    public RowObject getRowObject() {
        return rowObject;
    }

    public void setRowObject(RowObject rowObject) {
        this.rowObject = rowObject;
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
        if (!(object instanceof RowObjectPartRef)) {
            return false;
        }
        RowObjectPartRef other = (RowObjectPartRef) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.RowObjectPartRef[ id=" + id + " ]";
    }
    
}
