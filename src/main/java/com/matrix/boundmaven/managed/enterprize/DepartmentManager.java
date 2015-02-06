/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.Time;
import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import com.matrix.boundmaven.validators.UniqueDepartment;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
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
    @EJB DepartmentFacadeLocal departmentFacade;
    
    @NotBlank(message = "{notBlankDepartment.message}")
    @Size(min = 2,max = 45,message = "{departmentNameLength.message}")
    @NotNull
    //@UniqueDepartment
    private String departmentName;
    
    @Size(max = 255,message = "{departmentDescriptionLength.message}")
    private String departmentDescription;
    
    private Department editingDepartment;  // переданное подразделение для редактирования
   
    
    @PostConstruct
    private void initDepParams(){
    Map<String,Object> depmap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    // Map<String,Object> depmap = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    
    Boolean isNew = (Boolean)depmap.get("isNew");  // получение информации: создаем или редактируем подразделение
    
        if (isNew.equals(Boolean.FALSE)) {      //если подразделение передано для редактирования 
            Department depForEdit = (Department) depmap.get("dep");  // получение подразделения для редактирования
            editingDepartment = depForEdit;                          //копируем подразделение в специальное поле 
            this.departmentName = depForEdit.getDepartmentName();    
            this.departmentDescription = depForEdit.getDescription();
                  
        } else {   // если создается новое подразделение
            this.editingDepartment = null;
            this.departmentName = null;
            this.departmentDescription = null;
        }
   
    } 
    
    @PreDestroy
    private void destroy(){
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("dep"); 
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("isNew"); 
    }
    
    
     public void closeCreateDepartmentDialog() {

        Department dep = new Department();

        // String startDepartment = dep.getDepartmentName();
        List<Department> departmentFromDB = departmentFacade.getDepartmentByName(this.departmentName);

        if (departmentFromDB.isEmpty()) {
            dep.setDepartmentName(departmentName);
            dep.setDescription(departmentDescription);
            Time time = new Time();
            time.setInsertTime(new Date());
            dep.setCtime(time);
            RequestContext.getCurrentInstance().closeDialog(dep);
        } else {
            FacesMessage facesMessage = new FacesMessage("Подразделение с таким именем уже существует", dep.getDepartmentName());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }
    }
     
     public void closeCancelCreateDepartmentDialog() {
          RequestContext.getCurrentInstance().closeDialog(null);
     }
     
     
       
    public void closeEditDepartmentDialog() {

        Department dep = this.editingDepartment;   // редактируемое подразделение полученное в @PostConstruct из БД
        String editableDepartmentName = dep.getDepartmentName(); // исходное редактируемое имя подразделения из БД
        String editableDepartmentDescription = dep.getDescription(); // исходное редактируемое описание подразделения из БД
        // Проверяем есть ли введенное название подразделения в БД
        List<Department> departmentFromDB = departmentFacade.getDepartmentByName(departmentName); // 

        if (departmentFromDB.isEmpty()) {  // если введенное подразделение не существует
            dep.setDepartmentName(departmentName);  //  задаем имя подразделения 
            dep.setDescription(departmentDescription); // и описание
            dep.getCtime().setUpdateTime(new Date());  // время изменения
            RequestContext.getCurrentInstance().closeDialog(dep);
        } else // если подразделение уже существует
        {
            
            if (this.departmentName.equals(editableDepartmentName)) { // если имя подразделения не поменялось при редактированиии
                if (this.departmentDescription.equals(editableDepartmentDescription)/*&&(   )*/) { // и описание тоже не поменялось  
                    // переходим на исходную панель без изменений(признак этого dep = null)
                    FacesMessage message = new FacesMessage("Изменений не внесено");
                     FacesContext.getCurrentInstance().addMessage(null, message);    
                   //RequestContext.getCurrentInstance().closeDialog(null); 
                } else {   // поменялось описание подразделения или еще что -то в случае нескольких параметров
                    dep.setDepartmentName(departmentName);  //  задаем имя подразделения 
                    dep.setDescription(departmentDescription); // и описание
                    dep.getCtime().setUpdateTime(new Date());  // время изменения
                    RequestContext.getCurrentInstance().closeDialog(dep);
                }
            }else{ // имя поменялось при редактировании на уже существующее
                FacesMessage facesMessage = new FacesMessage("Подразделение с таким именем уже существует", dep.getDepartmentName());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        }
    }
    public void closeCancelEditDepartmentDialog() {
          RequestContext.getCurrentInstance().closeDialog(null);
     }
     
     
     
    public DepartmentManager() {
    }

     
    
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String   departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public Department getEditingDepartment() {
        return editingDepartment;
    }

    public void setEditingDepartment(Department editingDepartment) {
        this.editingDepartment = editingDepartment;
    }
   
    
    
    
}
