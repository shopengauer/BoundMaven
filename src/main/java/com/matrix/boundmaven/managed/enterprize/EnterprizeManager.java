/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.EmployeeRole;
import com.matrix.boundmaven.entity.JobTitle;
import com.matrix.boundmaven.entity.PhoneType;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.session.EmployeeFacadeLocal;
import com.matrix.boundmaven.session.JobTitleFacadeLocal;
import com.matrix.boundmaven.validators.UniqueDepartment;
import com.matrix.boundmaven.validators.UniqueJobTitle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javassist.bytecode.stackmap.BasicBlock;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.constraints.Digits;
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

    
    //Binding 
    private DataTable depDataTable;
    
    //Department
    private List<Department> departmentList;
    private List<Department> selectedDepartments;
    
    
    //JobTitle
    private List<JobTitle> jobTitleList;
    private List<JobTitle> selectedJobTitles;
    private List<JobTitle> jobsForDepartment; 
    //Employee
    private List<Employee> employeeList;
    private List<Employee> selectedEmployee;
    private List<JobTitle> dropDownJobTitleList; 
    
    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    @EJB
    JobTitleFacadeLocal jobTitleFacade;
    
    @EJB
    EmployeeFacadeLocal employeeFacade;
    
     
    
  //  private String currrentDep;
    
    
    //Department vars
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    @UniqueDepartment
    private String departmentName;
    
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String description;
    
    
    
    //JobTitle vars
    @NotBlank(message = "{notBlankJobTitle.message}")
    @UniqueJobTitle
    @Size(min = 2,max = 45,message = "{jobTitleNameLength.message}")
    private String jobTitle;
    
    
    @Digits(integer = 300000,fraction = 0)
    @Size(min = 2, max = 45)
    private String jobTitleSalary;
    
    private String jobTitleDepartment;
    
    //Employee vars
    private String firstname;
    private String lastname;
    private String account;
    private String password;
    private List<String> emails;
    private Map<PhoneType,String> phoneNumbers;
    private PhoneType phoneType;
    private String phoneMobile;
    private String phoneWorkMobile;
    private String phoneWork;
    private EmployeeRole employeeRole; // может нужно String
    private String employeeDepartment;
    private String employeeJobTitle;
    
    
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
    
    public void onCreateDepartmentListener(SelectEvent se){
        Department dep = (Department)se.getObject();
      //  departmentName = dep.getDepartmentName();
      //  description = dep.getDescription();
      if(dep != null){  
        departmentFacade.create(dep);
        departmentList = null;//обнуляем чтобы считать новый из базы????????????????????????????
      }       
// departmentName = null;
      //  description = null;
        
    }
    
    public void onEditDepartmentListener(SelectEvent se){
        Department dep = (Department)se.getObject();
      //  departmentName = dep.getDepartmentName();
      //  description = dep.getDescription();
      if(dep != null){
           departmentFacade.updateDepartment(dep);
           departmentList = null;//обнуляем чтобы считать новый из базы????????????????????????????
       } 
        
      //  departmentFacade.createDepartment(dep.getDepartmentName(), dep.getDescription());
        
       // departmentName = null;
      //  description = null;
        
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
    
    public void departmentJobsListener(ActionEvent event){
     //  dropDownJobTitleList  
     //  this.jobsForDepartment = jobTitleFacade. 
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
     
     public List<Employee> getAllEmployees(){
         
        if(employeeList == null){
           employeeList = employeeFacade.findAll();
         }  
        return employeeList; 
         
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
            List<Department> departmentFromDB = departmentFacade.getDepartmentByName(dep.getDepartmentName());
            if((departmentFromDB.isEmpty()) || (departmentFromDB.get(0).getDepartmentName().equals(startDepartment))){
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
    
 
    public void addEmployeeDialog()
    {
        RequestContext.getCurrentInstance().openDialog("addEmployeeDialog");
        
    }
    
      public void openCreateDepartmentDialog(Object o){
     
        if(o != null){  
          Department depForEdit = (Department)o;
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isNew", Boolean.FALSE);
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dep", o); 
          RequestContext.getCurrentInstance().openDialog("addDepartmentDialog");  
        }else{  
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isNew", Boolean.TRUE);
           RequestContext.getCurrentInstance().openDialog("addDepartmentDialog"); 
        }   
      }
    
     public void openCreateDepartmentDialog(){
      
//      Department depForC   
//      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dep", o);        
//      RequestContext.getCurrentInstance().openDialog("addDepartmentDialog");  
        
      } 
      
      
      
      
      
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
      
      
    
    
    public void closeDialog(){
        RequestContext.getCurrentInstance().closeDialog("close");
    }
    
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
    
     public EmployeeRole[] getEmployeeRoleConstants(){
        return EmployeeRole.values(); 
    }

    public PhoneType[] getAllPhoneTypes(){
        
       return PhoneType.values();
    } 
     
     
     
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(List<Employee> selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getJobTitleSalary() {
        return jobTitleSalary;
    }

    public void setJobTitleSalary(String jobTitleSalary) {
        this.jobTitleSalary = jobTitleSalary;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Map<PhoneType, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneWorkMobile() {
        return phoneWorkMobile;
    }

    public void setPhoneWorkMobile(String phoneWorkMobile) {
        this.phoneWorkMobile = phoneWorkMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeJobTitle() {
        return employeeJobTitle;
    }

    public void setEmployeeJobTitle(String employeeJobTitle) {
        this.employeeJobTitle = employeeJobTitle;
    }
 
    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<JobTitle> getJobsForDepartment() {
        return jobsForDepartment;
    }

    public void setJobsForDepartment(List<JobTitle> jobsForDepartment) {
        this.jobsForDepartment = jobsForDepartment;
    }

    
     
}
