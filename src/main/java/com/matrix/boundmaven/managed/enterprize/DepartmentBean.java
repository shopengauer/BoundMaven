/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.validators.UniqueDepartment;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Vasiliy
 */



//@RequestScoped
//@Model
@RequestScoped
@Named(value = "departmentBean")
public class DepartmentBean implements Serializable{

    private List<Department> departmentList;
    
    private List<Department> selectedDepartments;
    
    
    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    @Inject
    Conversation conversation;
    /**
     * Creates a new instance of DepartmentBean
     */
    
    private String currrentDep;

   
    
    
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    @UniqueDepartment
    private String departmentName;
   
    
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String description;
    
    @PostConstruct
    private void init(){
     //conversation.begin();
    }
    
    
    
    public DepartmentBean() {
     
    }

     public String getCurrrentDep() {
        return currrentDep;
    }

    public void setCurrrentDep(String currrentDep) {
        this.currrentDep = currrentDep;
    }
    
    
    
    
    public List<Department> getSelectedDepartments() {
        return selectedDepartments;
    }

    public void setSelectedDepartments(List<Department> selectedDepartments) {
        this.selectedDepartments = selectedDepartments;
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
    
   
   
    public void createDepartmentListener(ActionEvent ae){
        
        departmentFacade.createDepartment(departmentName, description);
        departmentName = null;
        description = null;
    }
    
    
    public String departmentInfo(){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> map =  fc.getExternalContext().getRequestParameterMap();
        currrentDep  = map.get("depName");
        return "departmentInfo";
        
    }
  
     
    public void getAllDepartmentActionListener(ActionEvent event) {
       
          departmentList = departmentFacade.findAll();
          
      } 
    
     public List<Department> getAllDepartment() {
       
          return departmentFacade.findAll();
          
      } 
 
     
     public void deleteDepartmentListener(ActionEvent event){
         
         
         if(!(selectedDepartments.isEmpty())){
              departmentFacade.deleteDpartmentList(selectedDepartments);
           }else
           {
            FacesMessage message = new FacesMessage("Не выбрано ни одно подразделение для удаления");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", message);
           }
     }
     
     public void onRowEdit(RowEditEvent event) {

    //     event.getComponent().
//        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage("dfvdfv", new FacesMessage(((Department)(event.getObject())).getDepartmentName()));
        
     }
     
    public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
}
