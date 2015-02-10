/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.JobTitle;
import com.matrix.boundmaven.entity.Time;
import com.matrix.boundmaven.managed.enterprize.qualifiers.Transmitter;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.session.JobTitleFacade;
import com.matrix.boundmaven.session.JobTitleFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Vasiliy
 */
@Named(value = "jobTitleManager")
@ViewScoped
public class JobTitleManager implements Serializable{

    
    @EJB
    JobTitleFacadeLocal jobTitleFacade;
    @EJB
    DepartmentFacadeLocal departmentFacade;
    
      
    @NotBlank(message = "{notBlankJobTitle.message}")
    @Size(min = 2,max = 45,message = "{jobTitleNameLength.message}") 
    @NotNull
    private String formJobTitleName;
    
   // @Digits(integer = 300000,fraction = 0)
   // @Size(min = 2, max = 45)
    private String formSalary;
    private String formDepartmentName;
    
    private JobTitle editingJobTitle; // переданная должность для редактирования
    
    @PostConstruct
    private void init(){
      Map<String,Object> depmap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
      Boolean isNew = (Boolean)depmap.get("isNew");  // получение информации: создаем или редактируем подразделение
    
        if (isNew.equals(Boolean.FALSE)) {      //если подразделение передано для редактирования 
            JobTitle jobForEdit = (JobTitle) depmap.get("job");  // получение подразделения для редактирования
            editingJobTitle = jobForEdit;                          //копируем подразделение в специальное поле 
            this.formJobTitleName = jobForEdit.getJobTitleName();
            this.formSalary = jobForEdit.getSalary();
            this.formDepartmentName = jobForEdit.getDepartment().getDepartmentName();
        } else {   // если создается новое подразделение
            this.editingJobTitle = null;
            this.formJobTitleName = null;
            this.formDepartmentName = null;
        }
     
    }
    
     @PreDestroy
    private void destroy(){
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("job"); 
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("isNew"); 
    }
    
    
     public void closeCreateJobTitleDialog() {

        JobTitle job = new JobTitle();
        // String startDepartment = dep.getDepartmentName();
        List<JobTitle> jobTitleListFromDB = jobTitleFacade.getJobTitleByName(this.formJobTitleName);

        if (jobTitleListFromDB.isEmpty()) {  // проверяем есть ли в базе данных такая должность
            job.setJobTitleName(this.formJobTitleName); // если еще нет такой должности
            job.setSalary(this.formSalary);
            job.setDepartment(departmentFacade.getDepartmentByName(this.formDepartmentName).get(0));  
            Time time = new Time();
            time.setInsertTime(new Date());
            job.setCtime(time);
            RequestContext.getCurrentInstance().closeDialog(job);
        } else {
            FacesMessage facesMessage = new FacesMessage("Должность с таким именем уже существует", job.getJobTitleName());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }
    }
 
     public void closeEditJobTitleDialog() {

        JobTitle job = this.editingJobTitle;   // редактируемая должность полученная в @PostConstruct из БД
        
        String editableJobTitleName = job.getJobTitleName(); // исходное редактируемое имя подразделения из БД
        String editableJobTitleSalary = job.getSalary(); // исходное редактируемое описание подразделения из БД
        Department editableJobTitleDepartment =  job.getDepartment();
        
        // Проверяем есть ли введенное название подразделения в БД
        List<JobTitle> jobTitleListFromDB = jobTitleFacade.getJobTitleByName(this.formJobTitleName); // 

        if (jobTitleListFromDB.isEmpty()) {  // если введенная должность не существует
            job.setJobTitleName(this.formJobTitleName);  //  задаем имя должности 
            job.setSalary(this.formSalary); // и описание
            job.setDepartment(departmentFacade.getDepartmentByName(this.formDepartmentName).get(0));
            job.getCtime().setUpdateTime(new Date());  // время изменения
            RequestContext.getCurrentInstance().closeDialog(job);  // переход к методу merge
 
            
        } else // если подразделение уже существует
        {
            if (this.formJobTitleName.equals(editableJobTitleName)) { // если имя должности не поменялось при редактированиии
                if (this.formSalary.equals(editableJobTitleSalary)&&
                        (this.formDepartmentName.equals(editableJobTitleDepartment.getDepartmentName()))) { // и описание тоже не поменялось  
                    // переходим на исходную панель без изменений(признак этого dep = null)
                     FacesMessage message = new FacesMessage("Изменений не внесено");
                     FacesContext.getCurrentInstance().addMessage(null, message);    
                   //RequestContext.getCurrentInstance().closeDialog(null); 
                } else {   // поменялось описание подразделения или еще что -то в случае нескольких параметров
                    job.setJobTitleName(this.formJobTitleName);  //  задаем имя должности 
                    job.setSalary(this.formSalary); // и описание
                    job.setDepartment(departmentFacade.getDepartmentByName(this.formDepartmentName).get(0));
                    job.getCtime().setUpdateTime(new Date());  // время изменения
                    RequestContext.getCurrentInstance().closeDialog(job);  // переход к методу merge
                }
            }else{ // имя поменялось при редактировании на уже существующее
                FacesMessage facesMessage = new FacesMessage("Должность с таким именем уже существует", job.getJobTitleName());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        }
    } 
      
     
   public void closeCancelCreateJobTitleDialog() {
          RequestContext.getCurrentInstance().closeDialog(null);
     }
        
   public void closeCancelEditJobTitleDialog() {
          RequestContext.getCurrentInstance().closeDialog(null);
     }  
     
    public JobTitle getEditingJobTitle() {
        return editingJobTitle;
    }

    public void setEditingJobTitle(JobTitle editingJobTitle) {
        this.editingJobTitle = editingJobTitle;
    }

    public String getFormJobTitleName() {
        return formJobTitleName;
    }

    public void setFormJobTitleName(String formJobTitleName) {
        this.formJobTitleName = formJobTitleName;
    }

    public String getFormSalary() {
        return formSalary;
    }

    public void setFormSalary(String formSalary) {
        this.formSalary = formSalary;
    }

    public String getFormDepartmentName() {
        return formDepartmentName;
    }

    public void setFormDepartmentName(String formDepartmentName) {
        this.formDepartmentName = formDepartmentName;
    }

   
    
}
