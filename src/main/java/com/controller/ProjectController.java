package com.controller;

import com.dto.ProjectRequest;
import com.exception.NotExistException;
import com.model.Project;
import com.service.ProjectService;
import com.validation.ValidateProject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Validated
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService service;

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody @Valid ProjectRequest request){
        return new ResponseEntity<>(service.addProject(request), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    @Cacheable(cacheNames = "project", key="#id")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) throws NotExistException {
        return ResponseEntity.ok(service.getProject(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Project>> getAllProjects() throws NotExistException {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @PutMapping("/updateById/{id}")
    @CachePut(cacheNames = "project", key="#id")
    public ResponseEntity<Project> updateProjectById(@PathVariable int id, @RequestBody @Valid ProjectRequest request) throws NotExistException{
        return ResponseEntity.ok(service.updateProject(id, request));
    }

    @DeleteMapping("/deleteById/{id}")
    @CacheEvict(cacheNames = "project", key="#id")
    public ResponseEntity<String> deleteProjectById(@PathVariable @ValidateProject int id) throws NotExistException {
        try{
            service.deleteProjectById(id);
            return ResponseEntity.ok("Project with ID: " + id + " successfully deleted");
        } catch (NotExistException notExistException) {
            return ResponseEntity.badRequest().body(notExistException.getMessage());
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body("Assigned project cannot be deleted");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllProjects(){
        try {
            service.deleteAllProjects();
            return ResponseEntity.ok("Successfully deleted all projects");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Assigned project cannot be deleted");
        }
    }
}
