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
    public void deleteDpartmentList(List<Department> departmentList) {
          
       ListIterator<Department> li = departmentList.listIterator();
       while(li.hasNext()){
           remove(li.next());
       }
       
    }
   
    
    
    
    
}
