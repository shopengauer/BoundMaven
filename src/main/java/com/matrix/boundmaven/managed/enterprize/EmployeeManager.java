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
import com.matrix.boundmaven.session.EmployeeFacadeLocal;
import com.matrix.boundmaven.session.JobTitleFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
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
    
    private String formFirstname;
    private String formLastname;
    private String formAccount;
    private String formPassword;
    
    @Size(min = 0,max = 1)
    private List<String> formEmails = new ArrayList<>();;
    
    @Email
    private String formEmail;
    
    private Map<PhoneType,String> phoneNumbers;
    private PhoneType phoneType;
    private String formPhoneMobile;
    private String formPhoneWorkMobile;
    private String formPhoneWork;
    private EmployeeRole formEmployeeRole; // может нужно String
    private String formEmployeeDepartment;
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
            this.formEmployeeRole = employeeForEdit.getEmployeeRole(); // может нужно String
            this.formEmployeeDepartment = employeeForEdit.getDepartment().getDepartmentName();
            this.formEmployeeJobTitle = employeeForEdit.getJobTitle().getJobTitleName();
                   
        } else {   // если создается новое подразделение
           this.editingEmployee = null;                          //копируем подразделение в специальное поле 
            this.formFirstname = null;
            this.formLastname =  null;
            this.formAccount = null;
            this.formPassword = null;
           //  formEmails = new ArrayList<>(); // создаем Email ArrayList 
           //  formEmails.add("Kukusa");
            this.formPhoneMobile = null;
            this.formPhoneWorkMobile = null;
            this.formPhoneWork = null;
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
    
    public void getJobTitlesForDepartmentListener(){
   //  String departmentName = (String)se.getObject();
     jobtitlesForDepartmentList = jobTitleFacade.getJobTitleListByDepartmentName(this.formEmployeeDepartment);
       
    }
    
    public void closeCreateEmployeeDialog(){
        
    }

    
    public void closeEditEmployeeDialog(){
        
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
    
    
    
    
    
}
