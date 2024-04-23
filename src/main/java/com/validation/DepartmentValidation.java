package com.validation;

import com.repository.DepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentValidation implements ConstraintValidator<ValidateDepartment, Integer> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public boolean isValid(Integer deptId, ConstraintValidatorContext constraintValidatorContext) {
        if(deptId == null){
            return false;
        } else {
            return departmentRepository.existsById(deptId);
        }
    }
}
