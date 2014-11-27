/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import com.matrix.boundmaven.session.DepartmentFacade;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Vasiliy
 */
@Named(value = "departmentBean")
@RequestScoped
public class DepartmentBean {

    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    /**
     * Creates a new instance of DepartmentBean
     */
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    private String departmentName;
   
    
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String description;
    
    
    public DepartmentBean() {
     
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
    
    public String createDepartment(){
        
    //  departmentFacade.createDepartment(departmentName, description);
      return null;
    }
    
   
    
    
    
}
