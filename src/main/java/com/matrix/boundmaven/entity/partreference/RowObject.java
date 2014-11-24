/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.partreference;

import com.matrix.boundmaven.entity.Time;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "VALUE",nullable = false)
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
    
    @OneToMany(mappedBy = "rowObject")
    private List<RowObjectReplacement> rowObjectReplacements;
    
     
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getRohs() {
        return rohs;
    }

    public void setRohs(String rohs) {
        this.rohs = rohs;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

    public List<RowObjectPartRef> getRowObjectPartRefs() {
        return rowObjectPartRefs;
    }

    public void setRowObjectPartRefs(List<RowObjectPartRef> rowObjectPartRefs) {
        this.rowObjectPartRefs = rowObjectPartRefs;
    }

    public List<RowObjectReplacement> getRowObjectReplacements() {
        return rowObjectReplacements;
    }

    public void setRowObjectReplacements(List<RowObjectReplacement> rowObjectReplacements) {
        this.rowObjectReplacements = rowObjectReplacements;
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
