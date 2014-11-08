/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "CONTRACT")
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTRACT_ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 100)
    @Column(name = "CONTRACT_NAME", nullable = false, unique = true, length = 100)
    private String contractName;
      
    @Basic(fetch = FetchType.LAZY, optional = true)
    @Size(min = Integer.MIN_VALUE, max = 255)
    @Column(name = "CONTRACT_DESCRIPTION", length = 255)
    private String description;
        
    
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "lowBound",column = @Column(name = "CONTRACT_LOW_BOUND",unique = true, nullable = false)),
                         @AttributeOverride(name = "highBound",column = @Column(name = "CONTRACT_HIGH_BOUND",unique = true, nullable = false))}) 
    private Bounds bounds;
      
    
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "insertTime",column = @Column(name = "CONTRACT_INSERT_TIME",nullable = false,updatable = false)),
                         @AttributeOverride(name = "updateTime",column = @Column(name = "CONTRACT_UPDATE_TIME"))})
    private Time time;
     
    
    @ManyToOne(optional = false)     // это Foreign Key от PK Device
    @JoinColumn(name = "FK_DEVICE_ID",referencedColumnName = "DEVICE_ID")  // name - имя FK это аннотация означает данная сущность собственник связи
    private Device device;
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_FACTORY_ID",referencedColumnName = "FACTORY_ID")
    private Factory factory;

    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY)
    private Collection<SubContract> subContracts; 
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
     
    @XmlTransient
    public Collection<SubContract> getSubContracts() {
        return subContracts;
    }

    public void setSubContracts(Collection<SubContract> subContracts) {
        this.subContracts = subContracts;
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
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.full.ContractF[ id=" + id + " ]";
    }
    
}
