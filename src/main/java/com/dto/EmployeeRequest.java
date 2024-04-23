package com.dto;

import com.validation.ValidateDepartment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "employee name cannot be null or empty string")
    @NotEmpty(message = "employee name cannot be empty string")
    private String name;

    @NotBlank(message = "position cannot be null or empty string")
    @NotEmpty(message = "position cannot be empty string")
    private String position;

    @ValidateDepartment
    private int deptId;
}
