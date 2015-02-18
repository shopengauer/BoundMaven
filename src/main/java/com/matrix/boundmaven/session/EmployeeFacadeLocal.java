/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface EmployeeFacadeLocal {

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    List<Employee> findRange(int[] range);

    int count();

    List<String> getAllDepartmentsName();

    List<Employee> getAllEmployeeByAccount(String account);
    
}
