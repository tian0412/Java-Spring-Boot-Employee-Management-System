package com.controller;

import com.dto.DepartmentRequest;
import com.dto.EmployeeRequest;
import com.model.Department;
import com.model.Employee;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    // uncomment EmployeeController.addEmployee()  -> @Valid
    @Test
    public void testAddEmployee() throws Exception {
        // Mocking department creation
        DepartmentRequest departmentRequest = DepartmentRequest.build("Test Department");
        Department department = Department.build(1, "Test Department");
        when(departmentService.addDepartment(departmentRequest)).thenReturn(department);

        // Mocking employee creation
        EmployeeRequest employeeRequest = EmployeeRequest.build("John Doe", "Manager", department.getDeptId());
        Employee employee = new Employee("John Doe", "Manager", department.getDeptId());
        when(employeeService.addEmployee(employeeRequest)).thenReturn(employee);

        // Perform POST request
        mockMvc.perform(post("/employee/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"position\":\"Manager\",\"deptId\": 1}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}

