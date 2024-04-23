package com.controller;

import com.dto.DepartmentRequest;
import com.model.Department;
import com.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DepartmentControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void testAddDepartment() throws Exception {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("Test Department");

        Department department = new Department();
        department.setDeptId(1);
        department.setName("Test Department");

        when(departmentService.addDepartment(any(DepartmentRequest.class))).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/department/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Department\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}





















//package com.controller;
//
//import com.dto.DepartmentRequest;
//import com.model.Department;
//import com.service.DepartmentService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@WebMvcTest(DepartmentController.class) // Specify the controller class to be tested
////@ExtendWith(MockitoExtension.class) // Enable Mockito support
////@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class DepartmentControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private DepartmentService departmentService;
//
//    @Test
//    void addDepartment() throws Exception {
////        mockMvc.perform(post("/add")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content("{\"name\":\"IT Department\"}"))
////                .andExpect(status().isCreated());
//        DepartmentRequest request = DepartmentRequest.build("IT Department");
//        Department department = Department.build(1, "IT Department");
//        when(departmentService.addDepartment(request)).thenReturn(department);
//
//        mockMvc.perform(post("http://localhost:8080/employee/add"))
//                .andDo(print())
//                .andExpect(status().isOk());
////                .andExpect(content().string())
//    }
//}

















//
//import com.model.Department;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class DepartmentControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void addDepartment() throws Exception {
//        mockMvc.perform(post("/add"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\":\"IT Department\"}"))
//        .andExpect(status().isCreated());
//
//    }
//}