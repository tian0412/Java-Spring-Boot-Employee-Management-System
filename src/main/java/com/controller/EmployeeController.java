package com.controller;

import com.dto.EmployeeRequest;
import com.exception.NotExistException;
import com.model.Employee;
import com.service.EmployeeService;
import com.validation.ValidateEmployee;
import com.validation.ValidateProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;


    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeRequest request){
        return new ResponseEntity<>(service.addEmployee(request), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    @Cacheable(cacheNames = "employee", key="#id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws NotExistException {
        return ResponseEntity.ok(service.getEmployee(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployee() throws NotExistException{
        return ResponseEntity.ok(service.getAllEmployee());
    }

    @PutMapping("/updateById/{id}")
    @CachePut(cacheNames = "employee", key="#id")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable int id, @RequestBody @Valid EmployeeRequest request) throws NotExistException{
        return ResponseEntity.ok(service.updateEmployee(id, request));
    }

    @PutMapping("{empId}/project/{projectId}")
    public Employee assignProjectToEmployee(@PathVariable @ValidateEmployee int empId, @PathVariable @ValidateProject int projectId){
        return service.assignProjectToEmployee(empId, projectId);
    }

    @DeleteMapping("/deleteById/{id}")
    @CacheEvict(cacheNames = "employee", key="#id")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) throws NotExistException {
        service.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee with ID: " + id + " successfully deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllEmployee(){
        service.deleteAllEmployee();
        return ResponseEntity.ok("Successfully deleted all employee");
    }
}
