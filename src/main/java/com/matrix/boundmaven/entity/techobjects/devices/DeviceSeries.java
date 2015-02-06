/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.techobjects.devices;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Василий
 */
@Entity
@Table(name = "DEVICE_SERIES")
public class DeviceSeries implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DECICE_SERIES_ID")
    private Long id;

    @Basic(optional = false)
    @Size(min = 2,max = 45)
    @Column(name = "DEVICE_SERIES_NAME",length = 45,unique = true)
    private String deviceSeriesName;
    
    @Basic(optional = true)
    @Size(max = 255)
    @Column(name = "DEVICE_SERIES_DESCRIPTION", length = 255)
    private String deviceSeriesDescription;
    
    
    @OneToMany(mappedBy = "deviceSeries",fetch = FetchType.LAZY)   
    private List<Device> devices;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceSeriesName() {
        return deviceSeriesName;
    }

    public void setDeviceSeriesName(String deviceSeriesName) {
        this.deviceSeriesName = deviceSeriesName;
    }

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
        //  Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeviceSeries)) {
            return false;
        }
        DeviceSeries other = (DeviceSeries) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.techobjects.DeviceSeries[ id=" + id + " ]";
    }
    
}
