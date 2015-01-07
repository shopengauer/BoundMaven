/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.validators;

import com.matrix.boundmaven.session.JobTitleFacadeLocal;
import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Vasiliy
 */
public class UniqueJobTitleValidator implements ConstraintValidator<UniqueJobTitle,String>{

    @EJB JobTitleFacadeLocal jobTitleFacadeLocal;
    
    @Override
    public void initialize(UniqueJobTitle constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
     
       return jobTitleFacadeLocal.getJobTitleByName(value).isEmpty();
        
    }
   
    
    
    
}
