package com.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmployeeValidation.class)
public @interface ValidateEmployee {
    public String message() default "Project cannot be assigned to an employee that does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
