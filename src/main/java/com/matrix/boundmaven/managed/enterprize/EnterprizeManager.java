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
import com.matrix.boundmaven.validators.UniqueJobTitle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.component.datatable.DataTable;
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

    
    //Binding 
    private DataTable depDataTable;
    
    //Department
    private List<Department> departmentList;
    private List<Department> selectedDepartments;
    
    
    //JobTitle
    private List<JobTitle> jobTitleList;
    private List<JobTitle> selectedJobTitles;
    
    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    @EJB
    JobTitleFacadeLocal jobTitleFacade;
    
//    @Inject
//    Conversation conversation;
    
    
  //  private String currrentDep;
    
    
    
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    @UniqueDepartment
    private String departmentName;
   
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String description;
    
    
    
    
    @NotBlank(message = "{notBlankJobTitle.message}")
    @UniqueJobTitle
    @Size(min = 2,max = 45,message = "{jobTitleNameLength.message}")
    private String jobTitle;
    
    
    @Digits(integer = 300000,fraction = 0)
    @Size(min = 2, max = 45)
    private String jobTitleSalary;
   
    private String jobTitleDepartment;
    
    
    
    @PostConstruct
    private void init(){
     
         selectedDepartments = new ArrayList<>();
         selectedJobTitles = new ArrayList<>();
        // departmentList  =  departmentFacade.findAll();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Новый бин",""));
    }
    
     /**
     * Creates a new instance of EnterprizeManager
     */
    
    public EnterprizeManager() {
     
    }

    public String getJobTitleDepartment() {
        return jobTitleDepartment;
    }

    public void setJobTitleDepartment(String jobTitleDepartment) {
        this.jobTitleDepartment = jobTitleDepartment;
    }

    
    
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSalary() {
        return jobTitleSalary;
    }

    public void setSalary(String salary) {
        this.jobTitleSalary = salary;
    }

    
    
    
//     public String getCurrrentDep() {
//        return currrentDep;
//    }
//
//    public void setCurrrentDep(String currrentDep) {
//        this.currrentDep = currrentDep;
//    }

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
       
     //   RequestContext.getCurrentInstance().update(":dataTableForm:dataTable");
        departmentFacade.createDepartment(departmentName, description);
        departmentList = null;//обнуляем чтобы считать новый из базы????????????????????????????
        departmentName = null;
        description = null;
    }
    
    public void createJobTitleListener(ActionEvent ae){
       
        jobTitleFacade.createJobTitle(jobTitle, jobTitleSalary, jobTitleDepartment);
        jobTitleList = null;
        jobTitle = null;
        jobTitleSalary = null;
    //    RequestContext.getCurrentInstance().update(":dataTableForm:dataTable");
       // departmentFacade.createDepartment(departmentName, description);
       // departmentList = null;//обнуляем чтобы считать новый из базы????????????????????????????
       // departmentName = null;
       // description = null;
    }
    
    
    public String departmentInfo(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> map =  fc.getExternalContext().getRequestParameterMap();
       // currrentDep  = map.get("depName");
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
           jobTitleList = jobTitleFacade.findAll();
         }  
        return jobTitleList;
     } 
      
      
     public void deleteDepartmentListener(ActionEvent event){
         
      //   RequestContext.getCurrentInstance().update(":dataTableForm:dataTable");
         if(!(selectedDepartments.isEmpty())){
              departmentFacade.deleteDepartmentList(selectedDepartments);
              departmentList = null;     
              jobTitleList = null;      
           }else
           {
            FacesMessage message = new FacesMessage("Не выбрано ни одно подразделение для удаления");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", message);
           }
    
     }
     
     
      
     
     public void deleteJobTitleListener(ActionEvent event){
         
       //  RequestContext.getCurrentInstance().update(":dataTableForm:dataTable");
         if(!(selectedJobTitles.isEmpty())){
              jobTitleFacade.deleteJobTitleList(selectedJobTitles);
              jobTitleList = null;
              
           }else
           {
            FacesMessage message = new FacesMessage("Не выбрана ни одна должность для удаления");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", message);
           }
    
     
     }
     
     
     public void onRowEdit(RowEditEvent event) {

        Department dep = (Department) event.getObject();
        String startDepartment = dep.getDepartmentName();
        
        
        if(dep.getDepartmentName().isEmpty()){
           FacesMessage facesMessage = new FacesMessage("Введите название подразделения", "Поле не должно быть пустым");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           departmentList = null; 
        }else
        {
            List<Department> jobTitleFromDB = departmentFacade.getDepartmentByName(dep.getDepartmentName());
            if((jobTitleFromDB.isEmpty()) || (jobTitleFromDB.get(0).getDepartmentName().equals(startDepartment))){
              //  if(dep.getDepartmentName().)   сделать чтобы измененния отображались если только что то реально изменено
                departmentFacade.updateDepartment(dep);
                FacesMessage facesMessage = new FacesMessage("Запись обновлена", dep.getDepartmentName());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                departmentList = null;
            } else {
                FacesMessage facesMessage = new FacesMessage("Подразделение с таким именем уже существует", dep.getDepartmentName());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                departmentList = null;
            }
            
            
            
        }    
        
        
        
//       
//        if (dep.getDepartmentName().isEmpty()) {
//
//            if (dep.getDepartmentName().isEmpty()) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Введите название подразделения", dep.getDepartmentName()));
//                departmentList = null;
//            } else {
//                departmentFacade.updateDepartment(dep);
//                departmentList = null;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Подразделение обновлено", dep.getDepartmentName()));
//            }
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Подразделение с таким именем уже существует!", dep.getDepartmentName()));
//            departmentList = null;
//            //RequestContext.getCurrentInstance().;
//        }

        // depDataTable.reset();
    }
   
     /**
      * 
      * @param event 
      */
      
     public void onRowEditJob(RowEditEvent event) {

        JobTitle job = (JobTitle) event.getObject();
        String startJobTitle = job.getJobTitleName();
        
        
        if (job.getJobTitleName().isEmpty()) {
            FacesMessage facesMessage = new FacesMessage("Введите название должности", "Поле не может быть пустым");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            jobTitleList = null;
        } else {
             List<JobTitle> jobTitleFromDB = jobTitleFacade.getJobTitleByName(job.getJobTitleName());  
             
            if ((jobTitleFromDB.isEmpty()) || (jobTitleFromDB.get(0).getJobTitleName().equals(startJobTitle))){
                jobTitleFacade.updateJobTitle(job);
                FacesMessage facesMessage = new FacesMessage("Запись обновлена", job.getJobTitleName());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                jobTitleList = null;
            } else {
                FacesMessage facesMessage = new FacesMessage("Должность с таким именем уже существует", job.getJobTitleName());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                jobTitleList = null;
            }

        }

    }
     
     
     
     
     
    public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    
          
    }
    
     public void onRowCancelJob(RowEditEvent event) {
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
