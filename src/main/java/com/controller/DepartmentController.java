package com.controller;

import com.dto.DepartmentRequest;
import com.exception.NotExistException;
import com.model.Department;
import com.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody @Valid DepartmentRequest request){
        return new ResponseEntity<>(service.addDepartment(request), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    @Cacheable(cacheNames = "department", key = "#id")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) throws NotExistException {
        return ResponseEntity.ok(service.getDepartment(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartments() throws NotExistException {
        return ResponseEntity.ok(service.getAllDepartment());
    }

    @PutMapping("/updateById/{id}")
    @CachePut(cacheNames = "department", key = "#id")
    public ResponseEntity<Department> updateDeptById(@PathVariable int id, @RequestBody @Valid DepartmentRequest request) throws NotExistException {
        return ResponseEntity.ok(service.updateDepartment(id, request));
    }

    @DeleteMapping("/deleteById/{id}")
    @CacheEvict(cacheNames = "department", key = "#id")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable int id) throws NotExistException {
        service.deleteDepartmentById(id);
        return ResponseEntity.ok("Department with ID " + id + " successfully deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllDepartments(){
        service.deleteAllDepartments();
        return ResponseEntity.ok("Successfully deleted all departments");
    }
}