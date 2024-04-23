package com.validation;

import com.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeValidation implements ConstraintValidator<ValidateEmployee, Integer> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(Integer empId, ConstraintValidatorContext constraintValidatorContext) {
        if (empId == null){
            return false;
        } else {
            return employeeRepository.existsById(empId);
        }
    }
}
