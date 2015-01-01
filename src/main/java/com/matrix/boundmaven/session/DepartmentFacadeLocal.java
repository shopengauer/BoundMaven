/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Department;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface DepartmentFacadeLocal {

    void create(Department department);

    void edit(Department department);

    void remove(Department department);

    Department find(Object id);

    List<Department> findAll();

    List<Department> findRange(int[] range);

    int count();

    void createDepartment(String departmentName, String departmentDescription);

    List<String> getDepartmentsNameList();

    List<Department> getDepartmentByName(String departmentName);

    void deleteDepartmentList(List<Department> departmentList);

    void updateDepartment(Department parameter);

   
    
}
