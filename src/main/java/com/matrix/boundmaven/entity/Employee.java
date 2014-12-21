/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import com.matrix.boundmaven.entity.notebook.Notebook;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "FIRSTNAME", nullable = false, length = 45)
    private String firstName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "LASTNAME", nullable = false, length = 45)
    private String lastName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 4,max = 45)
    @Column(name = "PASSWORD", nullable = false,length = 45)
    private String password;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "ACCOUNT", nullable = false, unique = true, length = 45)
    private String account;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "EMPLOYEE_ROLE")
    private EmployeeRole employeeRole;
    

    @ElementCollection
    @CollectionTable(name = "EMAILS",joinColumns = @JoinColumn(name = "EMPLOYEE_EMAIL_ID"))
    @Column(name = "EMAIL")
    private List<String> emails;
    
    @ElementCollection
    @CollectionTable(name = "EMPLOYEE_PHONE")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<PhoneType,String> phoneNumbers;
    
       
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private Department department;
     
    @ManyToOne(optional = false,fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_JOBTITLE_ID",referencedColumnName = "JOBTITLE_ID")
    private JobTitle  jobTitle;
    
    @Embedded
    private Time ctime;
        

//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<Device> devices;
//    
//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<DeviceType> deviceTypes;
//    
//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<DeviceVersion> deviceVersions;
//    
//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<TechDocEntity> techDocEntities;
//        
//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<TechDocType> techDocTypes;
//       
//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<TechDocFileTypeEntity> techDocFileTypeEntitys;
//    
//    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
//    private List<TechDocFilesBundleEntity> techDocFilesBundleEntitys;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Notebook> notebooks;
    
    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Map<PhoneType, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

//    public List<Device> getDevices() {
//        return devices;
//    }
//
//    public void setDevices(List<Device> devices) {
//        this.devices = devices;
//    }
//
//    public List<DeviceType> getDeviceTypes() {
//        return deviceTypes;
//    }
//
//    public void setDeviceTypes(List<DeviceType> deviceTypes) {
//        this.deviceTypes = deviceTypes;
//    }
//
//    public List<DeviceVersion> getDeviceVersions() {
//        return deviceVersions;
//    }
//
//    public void setDeviceVersions(List<DeviceVersion> deviceVersions) {
//        this.deviceVersions = deviceVersions;
//    }
//
//    public List<TechDocEntity> getTechDocEntitys() {
//        return techDocEntities;
//    }
//
//    public void setTechDocEntitys(List<TechDocEntity> techDocEntitys) {
//        this.techDocEntities = techDocEntitys;
//    }
//
//    public List<TechDocType> getTechDocTypes() {
//        return techDocTypes;
//    }
//
//    public void setTechDocTypes(List<TechDocType> techDocTypes) {
//        this.techDocTypes = techDocTypes;
//    }
//
//    public List<TechDocFileTypeEntity> getTechDocFileTypeEntitys() {
//        return techDocFileTypeEntitys;
//    }
//
//    public void setTechDocFileTypeEntitys(List<TechDocFileTypeEntity> techDocFileTypeEntitys) {
//        this.techDocFileTypeEntitys = techDocFileTypeEntitys;
//    }
//
//    public List<TechDocFilesBundleEntity> getTechDocFilesBundleEntitys() {
//        return techDocFilesBundleEntitys;
//    }
//
//    public void setTechDocFilesBundleEntitys(List<TechDocFilesBundleEntity> techDocFilesBundleEntitys) {
//        this.techDocFilesBundleEntitys = techDocFilesBundleEntitys;
//    }
//    
//   
    
    
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public List<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }
 
    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.full.Employee[ id=" + id + " ]";
    }
    
}
