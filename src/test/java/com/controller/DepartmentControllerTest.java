package com.controller;

import com.dto.DepartmentRequest;
import com.model.Department;
import com.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentControllerTest {

    @Test
    void addDepartment() {
        // Arrange
        DepartmentService departmentService = Mockito.mock(DepartmentService.class);
        DepartmentRequest request = DepartmentRequest.build("IT Department");
        Department dept = Department.build(0,"IT Department");

        // Act
        when(departmentService.addDepartment(request)).thenReturn(dept);

        // Assert
        assertEquals(dept, departmentService.addDepartment(request));
    }
}