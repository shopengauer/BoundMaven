/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
    
    @PostConstruct
    private void initDepParams(){
    Map<String,Object> depmap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    // Map<String,Object> depmap = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    Department depForEdit = (Department)depmap.get("dep");
    this.departmentName = depForEdit.getDepartmentName();
    this.departmentDescription = depForEdit.getDescription();
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("dep");
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
