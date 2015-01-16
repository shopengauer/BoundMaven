/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Vasiliy
 */
@Named(value = "departmentManager")
@ViewScoped
public class DepartmentManager implements Serializable{ 

    /**
     * Creates a new instance of DepartmentManager
     */
    private String departmentName;
    private String departmentDescription;
    
    
    
    public void openCreateDepartmentDialog(){
      RequestContext.getCurrentInstance().openDialog("addDepartmentDialog");  
    }
    
     public void closeCreateDepartmentDialog(){
         RequestContext.getCurrentInstance().closeDialog(Boolean.TRUE);
     }
    
    public DepartmentManager() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }
    
    
    
    
    
}
