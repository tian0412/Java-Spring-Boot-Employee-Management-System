package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor

public class DepartmentRequest {
    @NotBlank(message = "department name cannot be null or empty string")
    @NotEmpty(message = "department name cannot be empty string")
    private String name;
}
