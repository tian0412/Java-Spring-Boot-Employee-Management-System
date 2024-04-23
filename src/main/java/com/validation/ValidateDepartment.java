package com.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentValidation.class)
public @interface ValidateDepartment {
    public String message() default "Employee cannot be assigned to a department that does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
