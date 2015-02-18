/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.Employee;
import com.matrix.boundmaven.entity.JobTitle;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vasiliy
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal {
    @PersistenceContext(unitName = "BoundMavenPU")
    private EntityManager em;

    @EJB
    DepartmentFacadeLocal departmentFacade;
    
    @EJB
    JobTitleFacadeLocal jobTitleFacade;
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public List<String> getAllDepartmentsName() {
        TypedQuery<String> query = 
                em.createNamedQuery("Department.findAllDepartmentsName", String.class);
        return query.getResultList();
    }

    @Override
    public List<Employee> getAllEmployeeByAccount(String account) {
        
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findAllEmployeeByAccount", Employee.class);
       // query.setParameter("account",account).getResultList();
        
        return query.setParameter("account",account).getResultList();
    }

    @Override
    public void create(Employee employee) {
        
        Department cd = departmentFacade.getDepartmentByName(employee.getDepartment().getDepartmentName()).get(0);
        JobTitle jt = jobTitleFacade.getJobTitleByName(employee.getJobTitle().getJobTitleName()).get(0);
        employee.setDepartment(cd);
        employee.setJobTitle(jt);
        cd.getEmployees().add(employee);
        jt.getEmployees().add(employee);
        em.persist(employee);
       // super.create(employee); //To change body of generated methods, choose Tools | Templates.
    
    
    }
    
    
    
}
