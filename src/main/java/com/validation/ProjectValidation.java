package com.validation;

import com.repository.ProjectRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectValidation implements ConstraintValidator<ValidateProject, Integer> {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean isValid(Integer projectId, ConstraintValidatorContext constraintValidatorContext) {
        if (projectId == null){
            return false;
        } else {
            return projectRepository.existsById(projectId);
        }
    }
}
