package com.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProjectValidation.class)
public @interface ValidateProject {
    public String message() default "Project does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

