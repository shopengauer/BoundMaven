/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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
import org.hibernate.validator.constraints.NotBlank;

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

    
    @Basic(optional = false,fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "FIRSTNAME", nullable = false, length = 45)
    private String firstName;
    
    @Basic(optional = false,fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 2,max = 45)
    @Column(name = "LASTNAME", nullable = false, length = 45)
    private String lastName;
    
    @Basic(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 4,max = 45)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Basic(optional = false,fetch = FetchType.EAGER )
    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "ACCOUNT", nullable = false, unique = true, length = 45)
    private String account;

//    @Basic(optional = false, fetch = FetchType.EAGER)
//    @NotNull
//    @Size(min = 2,max = 45)
//    @Column(name = "JOBTITLE", nullable = false, length = 45)
//    private String jobTitle;
//    
    @ElementCollection
    @CollectionTable(name = "EMAILS",joinColumns = @JoinColumn(name = "EMOLOYEE_EMAIL_ID"))
    @Column(name = "EMAIL")
    private List<String> emails;
    
    @ElementCollection
    @CollectionTable(name = "EMPLOYEE_PHONE")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<PhoneType,String> phoneNumbers;
    
    
    //@ElementCollection
   // @CollectionTable()
    
    
    // Сделать отдельные ENTITY
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private Department department;
     
    @ManyToOne(optional = false,fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_JOBTITLE_ID",referencedColumnName = "JOBTITLE_ID")
    private JobTitle  jobTitle;
    
    
        

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
