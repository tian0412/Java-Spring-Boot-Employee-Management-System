package com.dto;

import com.model.Employee;
import com.validation.ValidateEmployee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "project name cannot be null or empty string")
    @NotEmpty(message = "project name cannot be empty string")
    private String name;

//    @ValidateEmployee
//    private List<Employee> employeeList;
}
