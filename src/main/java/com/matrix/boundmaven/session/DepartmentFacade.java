/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.Time;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
   
    
    
}
