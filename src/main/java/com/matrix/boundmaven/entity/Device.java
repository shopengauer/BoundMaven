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
@Table(name = "DEVICE")
public class Device implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEVICE_ID")
    private Long id;
  
    @Basic(fetch = FetchType.EAGER,optional = false)
    @NotNull
    @Size(min = 1,max = 45)
    @Column(name = "DEVICE_NAME", length = 45, nullable = false, unique = true)
    private String deviceName;
    
    @Size(min = 0,max = 255)
    @Basic(fetch = FetchType.LAZY,optional = true)
    @Column(name = "DEVICE_DESCRIPTION", length = 255)    
    private String deviceDescription;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "insertTime", column = @Column(name = "DEVICE_INSERT_TIME",nullable = false,updatable = false)),
                         @AttributeOverride(name = "updateTime", column = @Column(name = "DEVICE_UPDATE_TIME"))})
    private Time time;
    
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_TYPE_ID", referencedColumnName = "DEVICE_TYPE_ID")
    private DeviceType deviceType;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_VERSION_ID", referencedColumnName = "DEVICE_VERSION_ID")
    private DeviceVersion deviceVersion;
     
   
    @OneToMany(mappedBy = "device",fetch = FetchType.LAZY)
    private Collection<Contract> contracts;
 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public DeviceVersion getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(DeviceVersion deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }
  
    @XmlTransient 
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.full.DeviceF[ id=" + id + " ]";
    }
    
}
