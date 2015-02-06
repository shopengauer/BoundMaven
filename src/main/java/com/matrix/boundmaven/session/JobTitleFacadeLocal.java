/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import com.matrix.boundmaven.entity.JobTitle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface JobTitleFacadeLocal {

    void create(JobTitle jobTitle);

    void edit(JobTitle jobTitle);

    void remove(JobTitle jobTitle);

    JobTitle find(Object id);

    List<JobTitle> findAll();

    List<JobTitle> findRange(int[] range);

    int count();

    void createJobTitle(String jobTitleName, String jobTitleSalary, String department);

    List<JobTitle> getJobTitleByName(String jobTitleName);

    void deleteJobTitleList(List<JobTitle> jobTitleList);

    void updateJobTitle(JobTitle jobTitle);

    List<JobTitle> getJobTitleListByDepartmentName(String departmentName);
    
    
    
    
}
