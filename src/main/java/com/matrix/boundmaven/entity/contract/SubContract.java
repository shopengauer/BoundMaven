/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.contract;

import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.Time;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Василий
 */
@Entity
@Table(name = "SUBCONTRACT")
public class SubContract implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBCONTRACT_ID")
    private Long id;

    
    @Basic(optional = false,fetch = FetchType.EAGER)
    @Size(min = 2, max =45)
    @NotNull
    @Column(name = "SUBCONTRACT_NAME", nullable = false, unique = true, length = 45)
    private String subContractName;
    
     
    
    
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "lowBound", column = @Column(name = "SUBCONTRACT_LOW_BOUND",nullable = false, unique = true)),
                         @AttributeOverride(name = "highBound", column = @Column(name = "SUBCONTRACT_HIGH_BOUND",nullable = false, unique = true))})
    private Bounds bounds;
    
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "insertTime", column = @Column(name = "SUBCONTRACT_INSERT_TIME",nullable = false, updatable = false)),
                         @AttributeOverride(name = "updateTime", column = @Column(name = "SUBCONTRACT_UPDATE_TIME"))})
    private Time time;
   
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_CONTRACT_ID", referencedColumnName = "CONTRACT_ID")
    private Contract contract;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private Employee employee;
    
    
    
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
        //  Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubContract)) {
            return false;
        }
        SubContract other = (SubContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.full.SubContract[ id=" + id + " ]";
    }
    
}
