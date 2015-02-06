/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.contract;

import com.matrix.boundmaven.entity.Time;
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
@Table(name = "FACTORY")
public class Factory implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACTORY_ID")
    private Long id;
  
    @Basic(fetch = FetchType.EAGER,optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(length = 45, nullable = false, name = "FACTORY_NAME",unique = true)
    private String factoryName;
    
    @Basic(fetch = FetchType.LAZY, optional = true)
    @Size(min = 0, max = 255)
    @Column(length = 255, name = "FACTORY_DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "factory",fetch = FetchType.LAZY)
    private Collection<Contract> contracts;
    
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "insertTime",column = @Column(name = "FACTORY_INSERT_TIME",nullable = false,updatable = false)),
                         @AttributeOverride(name = "updateTime",column = @Column(name = "FACTORY_UPDATE_TIME"))})
    private Time time;
  
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient 
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
        if (!(object instanceof Factory)) {
            return false;
        }
        Factory other = (Factory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.full.FactoryF[ id=" + id + " ]";
    }
    
}
