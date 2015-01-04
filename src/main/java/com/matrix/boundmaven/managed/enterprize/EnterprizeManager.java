/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.JobTitle;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.session.JobTitleFacadeLocal;
import com.matrix.boundmaven.validators.UniqueDepartment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Vasiliy
 */



//@RequestScoped
//@Model
@ViewScoped
@Named(value = "enterprize")
public class EnterprizeManager implements Serializable{

    private List<Department> departmentList;
    private List<Department> selectedDepartments;
    
    private List<JobTitle> jobTitleList;
    private List<JobTitle> selectedJobTitles;
    
    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    @EJB
    JobTitleFacadeLocal jobTitleFacadeLocal;
    
    @Inject
    Conversation conversation;
    
   
    private String currrentDep;
   
    private DataTable depDataTable;
    
    
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    @UniqueDepartment
    private String departmentName;
   
    
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String description;
    
    
    private String jobTitle;
    private int salary;
    private Department jobTitleDepartment;
    
    
    
    @PostConstruct
    private void init(){
     
         selectedDepartments = new ArrayList<>();
        // departmentList  =  departmentFacade.findAll();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Новый бин",""));
    }
    
     /**
     * Creates a new instance of EnterprizeManager
     */
    
    public EnterprizeManager() {
     
    }

    public Department getJobTitleDepartment() {
        return jobTitleDepartment;
    }

    public void setJobTitleDepartment(Department jobTitleDepartment) {
        this.jobTitleDepartment = jobTitleDepartment;
    }

    
    
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    
    
    
     public String getCurrrentDep() {
        return currrentDep;
    }

    public void setCurrrentDep(String currrentDep) {
        this.currrentDep = currrentDep;
    }

    public DataTable getDepDataTable() {
        return depDataTable;
    }

    public void setDepDataTable(DataTable depDataTable) {
        this.depDataTable = depDataTable;
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

    public List<JobTitle> getJobTitleList() {
        return jobTitleList;
    }

    public void setJobTitleList(List<JobTitle> jobTitleList) {
        this.jobTitleList = jobTitleList;
    }

    public List<JobTitle> getSelectedJobTitles() {
        return selectedJobTitles;
    }

    public void setSelectedJobTitles(List<JobTitle> selectedJobTitles) {
        this.selectedJobTitles = selectedJobTitles;
    }
     
   
    public void createDepartmentListener(ActionEvent ae){
       
        RequestContext.getCurrentInstance().update(":dataTableForm:dataTable");
        departmentFacade.createDepartment(departmentName, description);
        departmentList = null;//обнуляем чтобы считать новый из базы????????????????????????????
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
        if(departmentList == null){
           departmentList = departmentFacade.findAll();
         }  
        return departmentList;
     } 
 
      public List<JobTitle> getAllJobTitles() {
         //  departmentList = null;
          if(jobTitleList == null){
           jobTitleList = jobTitleFacadeLocal.findAll();
         }  
        return jobTitleList;
     } 
      
      
     public void deleteDepartmentListener(ActionEvent event){
         
         RequestContext.getCurrentInstance().update(":dataTableForm:dataTable");
         if(!(selectedDepartments.isEmpty())){
              departmentFacade.deleteDepartmentList(selectedDepartments);
              departmentList = null;
           }else
           {
            FacesMessage message = new FacesMessage("Не выбрано ни одно подразделение для удаления");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", message);
           }
    
     
     }
     
     public void onRowEdit(RowEditEvent event) {
          
         //departmentName = ((Department)event.getObject()).getDepartmentName();
          Department dep = (Department)event.getObject();
         
          if(departmentFacade.getDepartmentByName(dep.getDepartmentName()).isEmpty()){
              
            if(dep.getDepartmentName().isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Введите название подразделения",dep.getDepartmentName())); 
                departmentList = null;
            }else{  
              departmentFacade.updateDepartment(dep);
              departmentList = null;
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Подразделение обновлено",dep.getDepartmentName()));
             }
          }else
          {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Подразделение с таким именем уже существует!",dep.getDepartmentName()));
              departmentList = null;
               //RequestContext.getCurrentInstance().;
          }
       
        // depDataTable.reset();
          
     }
   
    public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    
          
    }
 
    public void onRowSelect(SelectEvent event) {

//       
//         Department dep = (Department)event.getObject();
//         
//         
//         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Подразделение выделено",dep.getDepartmentName()));
//         
    }
    
 
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
    
    
    
}
