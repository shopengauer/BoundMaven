/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Vasiliy
 */
@Documented
@Constraint(validatedBy = UniqueDepartmentValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueDepartment {
    String message() default "{uniqueDepartment.message}";
    Class<?>[] groups() default {};
    Class<? extends  Payload>[] payload() default {};
    
}
