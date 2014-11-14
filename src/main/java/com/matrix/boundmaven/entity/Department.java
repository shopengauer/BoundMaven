/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    public Department() {
    }

     
    
    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }
     
    
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;
    
    @Basic(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 0, max = 45)
    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 45, unique = true)
    private String departmentName;
   
    @Basic(optional = true,fetch = FetchType.LAZY)
    @Size(max = 255)
    @Column(name = "DEPARTMENT_DESCRIPTION")
    private String description;
    
    
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees; 

    
    
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.Department[ id=" + id + " ]";
    }
    
}