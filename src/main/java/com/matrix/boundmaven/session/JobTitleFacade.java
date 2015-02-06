/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.JobTitle;
import com.matrix.boundmaven.entity.Time;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
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
public class JobTitleFacade extends AbstractFacade<JobTitle> implements JobTitleFacadeLocal {
    @PersistenceContext(unitName = "BoundMavenPU")
    private EntityManager em;

    @EJB DepartmentFacadeLocal departmentFacade;
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobTitleFacade() {
        super(JobTitle.class);
    }

    @Override
    public void createJobTitle(String jobTitleName, String jobTitleSalary, String department){
        
      //  TypedQuery<Department> query = em.createNamedQuery("", Department.class);
        List<Department> depList = departmentFacade.getDepartmentByName(department);
        Department dep =  depList.get(0);
                       
        JobTitle jobTitle = new JobTitle();
        jobTitle.setJobTitleName(jobTitleName);
        jobTitle.setSalary(jobTitleSalary);
        Time  time = new Time();
        time.setInsertTime(new Date());
        jobTitle.setCtime(time);
        jobTitle.setDepartment(dep);
        dep.getJobTitles().add(jobTitle);
        em.persist(jobTitle);

//   jobTitle. 
    
    
    }

    @Override
    public List<JobTitle> getJobTitleByName(String jobTitleName) {
        
        TypedQuery<JobTitle> query = em.createNamedQuery( "JobTitle.findJobTitleByName",JobTitle.class);
        return  query.setParameter("jobTitleName", jobTitleName).getResultList();
    }

    @Override
    public void deleteJobTitleList(List<JobTitle> jobTitleList) {
    
        ListIterator<JobTitle> iterator = jobTitleList.listIterator();
        while(iterator.hasNext()){
          JobTitle jobTitle = iterator.next();
         em.remove(em.find(JobTitle.class, jobTitle.getId()));
       }
    }   

    @Override
    public void updateJobTitle(JobTitle jobTitle) {

        JobTitle job = em.merge(jobTitle);
       // job.getCtime().setUpdateTime(new Date());

    }

    @Override
    public void create(JobTitle jobTitle) {
        List<Department> depList = departmentFacade.getDepartmentByName(jobTitle.getDepartment().getDepartmentName());
        Department dep =  depList.get(0);
        jobTitle.setDepartment(dep);
        dep.getJobTitles().add(jobTitle); // добавляем должность в List подразделений
        em.persist(jobTitle); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JobTitle> getJobTitleListByDepartmentName(String departmentName) {
        
         TypedQuery<JobTitle> query = em.createNamedQuery("JobTitle.findJobTitlesByDepartmentName",JobTitle.class);
       
         
         return  query.setParameter("departmentName", departmentName).getResultList();
        
    }
   
    
    
    
}
