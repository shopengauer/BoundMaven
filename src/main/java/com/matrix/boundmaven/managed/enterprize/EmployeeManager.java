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
import com.matrix.boundmaven.entity.Time;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.session.EmployeeFacadeLocal;
import com.matrix.boundmaven.session.JobTitleFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Vasiliy
 */
@Named(value = "employeeManager")
@ViewScoped
public class EmployeeManager implements Serializable{

    /**
     * Creates a new instance of EmployeeManagerDialog
     */
    
    @EJB
    EmployeeFacadeLocal employeeFacade;
    
    @EJB
    JobTitleFacadeLocal jobTitleFacade;
    
    @EJB
    DepartmentFacadeLocal departmentFacadeLocal;
    
    @NotBlank(message = "{notBlankFirstname.message}")
    private String formFirstname;
    @NotBlank(message = "{notBlankLastname.message}")
    private String formLastname;
    @NotBlank(message = "{notBlankAccount.message}")
    private String formAccount;
    @NotBlank(message = "{notBlankPassword.message}")
    private String formPassword;
    
    @Size(min = 0,max = 1)
    private List<String> formEmails;
    
    @Email
    @NotBlank
    private String formEmail;
    
    private Map<PhoneType,String> phoneNumbers;
    private PhoneType phoneType;
    private String formPhoneMobile;
    private String formPhoneWorkMobile;
    private String formPhoneWork;
    
    @NotBlank(message = "{notBlankEmployeeRole.message}")
    private EmployeeRole formEmployeeRole; // может нужно String
    
    @NotBlank(message = "{notBlankEmployeeRole.message}")
    private String formStringEmployeeRole;
    
    @NotBlank(message = "{notBlankDepartment.message}")
    private String formEmployeeDepartment;
    @NotBlank(message = "{notBlankJobTitle.message}")
    private String formEmployeeJobTitle;
    
    private List<JobTitle> jobtitlesForDepartmentList;
    
    private Employee editingEmployee;  // переданное подразделение для редактирования
    
    
    @PostConstruct
    private void initEmpParams(){
        
        
      Map<String,Object> empmap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
      Boolean isNew = (Boolean)empmap.get("isNew");  // получение информации: создаем или редактируем подразделение
     
      
        if (isNew.equals(Boolean.FALSE)) {      //если подразделение передано для редактирования 
            Employee employeeForEdit = (Employee) empmap.get("emp");  // получение подразделения для редактирования
            this.editingEmployee = employeeForEdit;                          //копируем подразделение в специальное поле 
            this.formFirstname = employeeForEdit.getFirstName();
            this.formLastname =  employeeForEdit.getLastName();
            this.formAccount = employeeForEdit.getAccount();
            this.formPassword = employeeForEdit.getPassword();
            this.formEmails = employeeForEdit.getEmails();
            this.formPhoneMobile = employeeForEdit.getPhoneNumbers().get(PhoneType.HOMEMOBILE);
            this.formPhoneWorkMobile = employeeForEdit.getPhoneNumbers().get(PhoneType.WORKMOBILE);
            this.formPhoneWork = employeeForEdit.getPhoneNumbers().get(PhoneType.WORK);
            this.formStringEmployeeRole = employeeForEdit.getEmployeeRole().name(); // может нужно String
            this.formEmployeeDepartment = employeeForEdit.getDepartment().getDepartmentName();
            this.formEmployeeJobTitle = employeeForEdit.getJobTitle().getJobTitleName();
                   
        } else {   // если создается новое подразделение
           this.editingEmployee = null;                          //копируем подразделение в специальное поле 
            this.formFirstname = null;
            this.formLastname =  null;
            this.formAccount = null;
            this.formPassword = null;
             formEmails = new ArrayList<>(); // создаем Email ArrayList 
            // formEmails.add("Kukusa");
            this.formPhoneMobile = null;
            this.formPhoneWorkMobile = null;
            this.formPhoneWork = null;
            phoneNumbers = new HashMap<>();
            this.formEmployeeRole = null; // может нужно String
            this.formEmployeeDepartment = null;
            this.formEmployeeJobTitle = null;
        }
   
    }
    
    @PreDestroy
    private void destroy(){
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("emp"); 
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("isNew"); 
    }
    
    public EmployeeManager() {
    }

    
    
    
    public EmployeeRole[] getEmployeeRoleConstants(){
        return EmployeeRole.values(); 
    }

    public PhoneType[] getAllPhoneTypes(){
        
       return PhoneType.values();
    } 
    
    public void getJobTitlesForDepartmentListener(ValueChangeEvent event){
   //  String departmentName = (String)se.getObject();
      String empDep = (String)event.getNewValue();
      jobtitlesForDepartmentList = jobTitleFacade.getJobTitleListByDepartmentName(empDep);
       
    }
    
    public void closeCreateEmployeeDialog(){
        //TODO: closeCreateEmployeeDialog() 
        Employee emp = new Employee();
          List<Employee> employeeListFromDB = employeeFacade.getAllEmployeeByAccount(this.formAccount);

        if (employeeListFromDB.isEmpty()) {  // проверяем есть ли в базе данных такая должность
            emp.setFirstName(this.formFirstname); // если еще нет такой должности
            emp.setLastName(this.formLastname);
            emp.setAccount(this.formAccount);
            emp.setPassword(this.formPassword);
            emp.setEmails(this.formEmails);
            phoneNumbers.put(PhoneType.HOMEMOBILE,formPhoneMobile);
            phoneNumbers.put(PhoneType.HOMEMOBILE,formPhoneWorkMobile);
            phoneNumbers.put(PhoneType.HOMEMOBILE,formPhoneWork);
            emp.setPhoneNumbers(phoneNumbers);
         //   formEmployeeRole
            emp.setEmployeeRole(PhoneType.valueOf(EmployeeRole.class, EmployeeRole.fromString(formStringEmployeeRole).toString()));
            
            Department cd = departmentFacadeLocal.getDepartmentByName(formEmployeeDepartment).get(0);
            emp.setDepartment(cd);
            //cd.getEmployees().add(emp);
            
            JobTitle cj = jobTitleFacade.getJobTitleByName(formEmployeeJobTitle).get(0);
            emp.setJobTitle(cj);
            //cj.getEmployees().add(emp);
           
            Time time = new Time();
            time.setInsertTime(new Date());
            emp.setCtime(time);
            RequestContext.getCurrentInstance().closeDialog(emp);
            
//            job.setDepartment(departmentFacade.getDepartmentByName(this.formDepartmentName).get(0));  
//            Time time = new Time();
//            time.setInsertTime(new Date());
//            job.setCtime(time);
//            RequestContext.getCurrentInstance().closeDialog(job);
        } else {
            FacesMessage facesMessage = new FacesMessage("Сотрудник с таким аккаунтом уже существует", emp.getAccount());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }
        
        
        
        
    }

    
    public void closeEditEmployeeDialog(){
        //TODO: closeEditEmployeeDialog() 
    }
    
    public void closeCancelCreateEmployeeDialog() {
          RequestContext.getCurrentInstance().closeDialog(null);
     }
    
    public void closeCancelEditEmployeeDialog() {
          RequestContext.getCurrentInstance().closeDialog(null);
     }

    public Employee getEditingEmployee() {
        return editingEmployee;
    }

    public void setEditingEmployee(Employee editingEmployee) {
        this.editingEmployee = editingEmployee;
    }

    public List<JobTitle> getJobtitlesForDepartmentList() {
        return jobtitlesForDepartmentList;
    }

    public void setJobtitlesForDepartmentList(List<JobTitle> jobtitlesForDepartmentList) {
        this.jobtitlesForDepartmentList = jobtitlesForDepartmentList;
    }

    public String getFormFirstname() {
        return formFirstname;
    }

    public void setFormFirstname(String formFirstname) {
        this.formFirstname = formFirstname;
    }

    public String getFormLastname() {
        return formLastname;
    }

    public void setFormLastname(String formLastname) {
        this.formLastname = formLastname;
    }

    public String getFormAccount() {
        return formAccount;
    }

    public void setFormAccount(String formAccount) {
        this.formAccount = formAccount;
    }

    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    public List<String> getFormEmails() {
        return formEmails;
    }

    public void setFormEmails(List<String> formEmails) {
        this.formEmails = formEmails;
    }

    public String getFormPhoneMobile() {
        return formPhoneMobile;
    }

    public void setFormPhoneMobile(String formPhoneMobile) {
        this.formPhoneMobile = formPhoneMobile;
    }

    public String getFormPhoneWorkMobile() {
        return formPhoneWorkMobile;
    }

    public void setFormPhoneWorkMobile(String formPhoneWorkMobile) {
        this.formPhoneWorkMobile = formPhoneWorkMobile;
    }

    public String getFormPhoneWork() {
        return formPhoneWork;
    }

    public void setFormPhoneWork(String formPhoneWork) {
        this.formPhoneWork = formPhoneWork;
    }

    public EmployeeRole getFormEmployeeRole() {
        return formEmployeeRole;
    }

    public void setFormEmployeeRole(EmployeeRole formEmployeeRole) {
        this.formEmployeeRole = formEmployeeRole;
    }

    public String getFormEmployeeDepartment() {
        return formEmployeeDepartment;
    }

    public void setFormEmployeeDepartment(String formEmployeeDepartment) {
        this.formEmployeeDepartment = formEmployeeDepartment;
    }

    public String getFormEmployeeJobTitle() {
        return formEmployeeJobTitle;
    }

    public void setFormEmployeeJobTitle(String formEmployeeJobTitle) {
        this.formEmployeeJobTitle = formEmployeeJobTitle;
    }

    public String getFormEmail() {
        return formEmail;
    }

    public void setFormEmail(String formEmail) {
        this.formEmail = formEmail;
    }

    public String getFormStringEmployeeRole() {
        return formStringEmployeeRole;
    }

    public void setFormStringEmployeeRole(String formStringEmployeeRole) {
        this.formStringEmployeeRole = formStringEmployeeRole;
    }
    
    
    
   public void addEmailToList(){
      
       if(formEmails.size() < 3 && !formEmails.contains(formEmail)){
           this.formEmails.add(formEmail);
           formEmail = null;
       }
       else{
           FacesMessage message = new FacesMessage("Количество адресов не должно превышать 3 или уже существует");
           FacesContext.getCurrentInstance().addMessage("growl", message);
//           FacesContext.getCurrentInstance().addMessage(formEmail, message);
           formEmail = null;
       }
       
   } 
    
   public void deleteEmailToList(String email){
      
       if(formEmails.size() > 0 ){
           this.formEmails.remove(email);
           formEmail = null;
        
       }
       else{
           FacesMessage message = new FacesMessage("Количество адресов не должно превышать 3 или уже существует");
           FacesContext.getCurrentInstance().addMessage("growl", message);
//           FacesContext.getCurrentInstance().addMessage(formEmail, message);
           formEmail = null;
       }
       
   }  
    
}
