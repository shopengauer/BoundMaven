/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.Time;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vasiliy
 */
@Stateless
public class DepartmentFacade extends AbstractFacade<Department> implements DepartmentFacadeLocal {
    @PersistenceContext(unitName = "BoundMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }

    @Override
    public void createDepartment(String departmentName, String departmentDescription) {
         Department department = new Department();
         department.setDepartmentName(departmentName);
         department.setDescription(departmentDescription);
         Time insertTime = new Time();
         insertTime.setInsertTime(new Date());
         department.setCtime(insertTime);
         create(department);
    }

    @Override
    public void create(Department entity) {
      //  super.create(entity); //To change body of generated methods, choose Tools | Templates.
        em.persist(entity);
    }

    
    
    
    @Override
    public List<String> getDepartmentsNameList() {
        TypedQuery<String> query = 
                em.createNamedQuery("Department.findAllDepartmentsName", String.class);
        return query.getResultList();
    }

    @Override
    public List<Department> getDepartmentByName(String departmentName) {
        
        TypedQuery<Department> query = 
                em.createNamedQuery("Department.findDepartmentByName", Department.class);
         
        return query.setParameter("departmentName", departmentName).getResultList();
    }

   
    
    
    
    @Override
    public void deleteDepartmentList(List<Department> departmentList) {
          
       ListIterator<Department> li = departmentList.listIterator();
       while(li.hasNext()){
          remove(li.next());
           
       }
       
    }

    @Override
    public void updateDepartment(Department dep) {
       Department upDepartment = em.merge(dep);
       //upDepartment.getCtime().setUpdateTime(new Date());
    }

    @Override
    public List<Department> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
  
    
    
    
}
