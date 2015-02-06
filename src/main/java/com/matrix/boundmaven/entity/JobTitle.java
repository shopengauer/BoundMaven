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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({@NamedQuery(name = "JobTitle.findJobTitleNameByName", query = "SELECT j.jobTitleName FROM JobTitle j WHERE j.jobTitleName = :jobTitleName"),
               @NamedQuery(name = "JobTitle.findAllJobTitle", query = "SELECT j FROM JobTitle j"),
               @NamedQuery(name = "JobTitle.findJobTitleByName", query = "SELECT j FROM JobTitle j WHERE j.jobTitleName = :jobTitleName"),
               @NamedQuery(name = "JobTitle.findJobTitlesByDepartmentName", query = "SELECT j FROM JobTitle j WHERE j.department.departmentName = :departmentName")}) // TODO: Query implementation
public class JobTitle implements Serializable,Comparable<JobTitle> {
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
    //@Size(min = 2, max = 45)
    @Column(name = "SALARY")
    private String salary;
    
    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_DEPARTMENT_ID",referencedColumnName = "DEPARTMENT_ID")
    private Department department;
    
    @OneToMany(mappedBy = "jobTitle",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Employee> employees; 
    
    @Embedded
    private Time ctime;

    public JobTitle() {
    }
    
    
    
    
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
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
        //  Warning - this method won't work in the case the id fields are not set
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

    @Override
    public int compareTo(JobTitle o) {
       
        

       return 1;   
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
