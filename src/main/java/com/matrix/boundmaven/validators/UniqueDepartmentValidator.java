/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.validators;

import com.matrix.boundmaven.session.DepartmentFacadeLocal;
import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Vasiliy
 */

public class UniqueDepartmentValidator implements ConstraintValidator<UniqueDepartment,String>{
 
     @EJB DepartmentFacadeLocal departmentFacade;
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return departmentFacade.getDepartmentByName(value).isEmpty(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(UniqueDepartment constraintAnnotation) {
       
    }
    
}
