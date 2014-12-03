/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Vasiliy
 */



//@RequestScoped
//@Model
@ConversationScoped
@Named(value = "departmentBean")
public class DepartmentBean implements Serializable{

    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    @Inject
    Conversation conversation;
    /**
     * Creates a new instance of DepartmentBean
     */
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    private String departmentName;
   
    
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String description;
    
    @PostConstruct
    private void init(){
     conversation.begin();
    }
    
    
    
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
   
    
// Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    // flash.putNow("depName", this.departmentName);
   //  flash.putNow("depDesc", this.description);    
    //departmentFacade.createDepartment(departmentName, description);
      return "addDepartmentConfirm?faces-redirect=true";
    }
     public String departmentConfirm(){
       
        departmentFacade.createDepartment(departmentName, description);
        conversation.end();
   
     //  departmentFacade.createDepartment(departmentName, description);
      return "windows/department/addDepartmentWin?faces-redirect=true";
    }
   public void actionListener(ActionEvent event) {
       
     
   } 
    
    
    
}
