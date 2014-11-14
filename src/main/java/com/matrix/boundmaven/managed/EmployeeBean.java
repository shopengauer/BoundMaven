/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.session.DepartmentFacade;
import com.matrix.boundmaven.session.EmployeeFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

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
    @EJB EmployeeFacade employeeFacade; 
    
    @EJB DepartmentFacade departmentFacade;
    
     
     
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

    
    private String departmentName;
     
    
    private String jobTitle;
    
    
    @PostConstruct
    private void initDepartmentList(){
        
      List<Department> departmentList = departmentFacade.findAll();
       
    };
    
    
    
    
     public EmployeeBean() {
    }
  
  
    public void createEmployee(){
        
        
    } 
     
     
    
    
}
