/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects;

import com.matrix.boundmaven.entity.Employee;
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
@Table(name = "TECHDOC_TYPE")
public class TechDocType implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TECHDOC_TYPE_ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_TYPE", nullable = false,unique = true)
    private String techDocType;
    
    
    @Basic    
    @Size(min = 2,max = 45)
    @Column(name = "TECHDOC_TARGET")
    private String techDocTarget;  // составное устройство или отдельная деталь
    
    
    @Basic
    @Column(name = "TECHDOC_TYPE_DESCRIPTION")
    private String techDocTypeDescription;
    
    
    @OneToMany(mappedBy = "techDocType")
    private List<TechDocEntity> techDocEntities; 
    
    @Embedded
    private Time ctime; 

    @ManyToOne(optional = true)
    @JoinColumn(name = "FK_EMPLOYEE_ID",referencedColumnName = "EMPLOYEE_ID")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
       
    public String getTechDocType() {
        return techDocType;
    }

    public void setTechDocType(String techDocType) {
        this.techDocType = techDocType;
    }

    public String getTechDocTarget() {
        return techDocTarget;
    }

    public void setTechDocTarget(String techDocTarget) {
        this.techDocTarget = techDocTarget;
    }

    public List<TechDocEntity> getTechDocEntities() {
        return techDocEntities;
    }

    public void setTechDocEntities(List<TechDocEntity> techDocEntities) {
        this.techDocEntities = techDocEntities;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

    public String getTechDocTypeDescription() {
        return techDocTypeDescription;
    }

    public void setTechDocTypeDescription(String techDocTypeDescription) {
        this.techDocTypeDescription = techDocTypeDescription;
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
        if (!(object instanceof TechDocType)) {
            return false;
        }
        TechDocType other = (TechDocType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.SchemeType[ id=" + id + " ]";
    }
    
}
