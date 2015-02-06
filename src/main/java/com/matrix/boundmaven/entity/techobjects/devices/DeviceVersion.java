/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects.devices;

import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.Time;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
 * @author Василий
 */
@Entity
@Table(name = "DEVICE_VERSION")
public class DeviceVersion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEVICE_VERSION_ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1,max = 45)
    @Column(name = "DEVICE_VERSION", nullable = false, unique = true)
    private String deviceVersion;
     
    
    @Basic
    @Column(name = "DEVICE_VERSION_DESCRIPTION")
    private String deviceVersionDescription;
    
    @Embedded
    private Time ctime;
    
    @OneToMany(mappedBy = "deviceVersion",fetch = FetchType.LAZY)
    private List<Device> devices;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

   
    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceVersionDescription() {
        return deviceVersionDescription;
    }

    public void setDeviceVersionDescription(String deviceVersionDescription) {
        this.deviceVersionDescription = deviceVersionDescription;
    }

    
    
    @XmlTransient 
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
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
        if (!(object instanceof DeviceVersion)) {
            return false;
        }
        DeviceVersion other = (DeviceVersion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.full.DeviceVersion[ id=" + id + " ]";
    }
    
}
