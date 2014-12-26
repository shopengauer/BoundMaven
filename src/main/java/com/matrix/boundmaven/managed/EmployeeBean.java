/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.session.EmployeeFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Василий
 */
@Named(value = "employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

    /**
     * Creates a new instance of EmployeeBean
     */
    @EJB EmployeeFacadeLocal employeeFacade; 
    
    @EJB DepartmentFacadeLocal departmentFacade;
    
      private UploadedFile file;
     
    @Size(min = 2,max = 45,message = "{firstnameLength.message}")
    @NotBlank(message = "{notBlankFirstname.message}")
    private String firstName;
     
     
    @Size(min = 2,max = 45,message = "{lastnameLength.message}")
    @NotBlank(message = "{notBlankLastname.message}") 
    private String lastName;
    
     
    @Size(min = 6,max = 45, message = "{password.message}")
    @NotBlank(message = "{notBlankPassword.massage}")
    private String password;
 
    
    @Size(min = 2, max = 45)
    @NotBlank(message = "{notBlankAccount.message}")
    private String account;

    @Size(min = 2, max = 45)
    @NotBlank(message = "{notBlankAccount.message}")
    private String departmentName;
     
    @NotBlank
    @Size(min = 2,max = 45,message = "{jobTitleLength.messages}")
    private String jobTitle;
    
    
    @PostConstruct
    private void initDepartmentList(){
        
      List<Department> departmentList = departmentFacade.findAll();
       
    };
    
   
    
     public EmployeeBean() {
    }
  
  
    public void createEmployee(){
        
        
    } 

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getDepartmentNameList(){
        
        return departmentFacade.getDepartmentsNameList();
    }
    
    
    
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
     
     
    
    
}
