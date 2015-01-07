/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.JobTitle;
import java.util.List;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobTitleFacade() {
        super(JobTitle.class);
    }

    @Override
    public void createJobTitle(String jobTitleName, String jobTitleSalary, String department){
        
        JobTitle jobTitle = new JobTitle();
        jobTitle.setJobTitleName(jobTitleName);
        jobTitle.setSalary(jobTitleSalary);
       
     //   jobTitle. 
    
    
    }

    @Override
    public List<JobTitle> getJobTitleByName(String jobTitleName) {
        
        TypedQuery<JobTitle> query = em.createNamedQuery( "JobTitle.findJobTitleByName",JobTitle.class);
        return  query.setParameter("jobTitleName", jobTitleName).getResultList();
    }
   
    
    
    
    
    
    
}
