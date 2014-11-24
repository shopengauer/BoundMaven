/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "JOBTITLE")
public class JobTitle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOBTITLE_ID")
    private Long id;
 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "JOBTITLE_NAME",nullable = false,unique = true)
    private String jobTitleName;
    
    
    @Basic(optional = true)
    @Size(min = 2, max = 45)
    @Column(name = "SALARY")
    private int salary;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "FK_DEPARTMENT_ID",referencedColumnName = "DEPARTMENT_ID")
    private Department department;
    
    @OneToMany(mappedBy = "jobTitle",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Employee> employees; 
    
    @Embedded
    private Time ctime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
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
        if (!(object instanceof JobTitle)) {
            return false;
        }
        JobTitle other = (JobTitle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.JobTitle[ id=" + id + " ]";
    }
    
}
