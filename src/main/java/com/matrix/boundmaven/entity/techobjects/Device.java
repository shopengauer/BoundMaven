/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects;

import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.Time;
import com.matrix.boundmaven.entity.partreference.RowObjectPartRef;
import java.io.Serializable;
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
  
    // Device Name 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "DEVICE_NAME", length = 45, nullable = false, unique = true)
    private String deviceName;
    
    @Basic
    @Size(min = 2,max = 45)
    @Column(name = "DEVICE_COD",length = 45, unique = true)
    private String deviceCod;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "FK_DEVICE_SERIES_ID",referencedColumnName = "DECICE_SERIES_ID")
    private String deviceSeriesName;
    // Признак того является ли устройсво верхним уровнем
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_PARENT_DEVICE",nullable = false)
    private Boolean isParentDevice;
    
    
    @Basic(optional = true)
    @Size(min = 0, max = 255)
    @Column(name = "DEVICE_DESCRIPTION", length = 255)    
    private String deviceDescription;
    
    // Device Type
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_TYPE_ID", referencedColumnName = "DEVICE_TYPE_ID")
    private DeviceType deviceType;
    
    // Device Version
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEVICE_VERSION_ID", referencedColumnName = "DEVICE_VERSION_ID")
    private DeviceVersion deviceVersion;
    
    // Device Description 
    
    @OneToMany(mappedBy = "device")
    private List<TechDocEntity> techDocEntities;

    
    // Отношение устройства к другим устройствам той же сущности
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "PARENT_DEVICE_ID", referencedColumnName = "DEVICE_ID")
    private Device parentDevice;
    
    @OneToMany(mappedBy = "parentDevice",fetch = FetchType.LAZY)
    private List<Device> childDevices;
    
    
    @Embedded
    private Time ctime;
   
    
    @OneToMany(mappedBy = "device")
    private List<RowObjectPartRef> rowObjectPartRefs;
     
    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_EMPLOYEE_ID",referencedColumnName = "EMPLOYEE_ID")
    private Employee employee;
        
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RowObjectPartRef> getRowObjectPartRefs() {
        return rowObjectPartRefs;
    }

    public void setRowObjectPartRefs(List<RowObjectPartRef> rowObjectPartRefs) {
        this.rowObjectPartRefs = rowObjectPartRefs;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

       
    
    public Time getTime() {
        return ctime;
    }

    public void setTime(Time time) {
        this.ctime = time;
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
  
//    @XmlTransient 
//    public Collection<Contract> getContracts() {
//        return contracts;
//    }
//
//    public void setContracts(Collection<Contract> contracts) {
//        this.contracts = contracts;
//    }

    public Boolean getIsParentDevice() {
        return isParentDevice;
    }

    public void setIsParentDevice(Boolean isParentDevice) {
        this.isParentDevice = isParentDevice;
    }

    public List<TechDocEntity> getTechDocEntities() {
        return techDocEntities;
    }

    public void setTechDocEntities(List<TechDocEntity> techDocEntities) {
        this.techDocEntities = techDocEntities;
    }

    public Device getParentDevice() {
        return parentDevice;
    }

    public void setParentDevice(Device parentDevice) {
        this.parentDevice = parentDevice;
    }

    public List<Device> getChildDevices() {
        return childDevices;
    }

    public void setChildDevices(List<Device> childDevices) {
        this.childDevices = childDevices;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }
//
//    public List<BomFile> getBomFiles() {
//        return bomFiles;
//    }
//
//    public void setBomFiles(List<BomFile> bomFiles) {
//        this.bomFiles = bomFiles;
//    }
//    
   
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
