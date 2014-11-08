/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    
    @Size(min = 2,max = 10,message = "{firstnameLength.message}")
    @NotBlank(message = "{notBlankLastname.message}")
    private String name;
    
    
    
    
    
    
     public EmployeeBean() {
    }
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    
}
