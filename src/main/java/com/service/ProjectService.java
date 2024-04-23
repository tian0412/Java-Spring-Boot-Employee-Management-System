package com.service;

import com.dto.ProjectRequest;
import com.exception.NotExistException;
import com.model.Project;
import com.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    public Project addProject(ProjectRequest request){
        Project project = new Project(request.getName());
        return repository.save(project);
    }

    public List<Project> getAllProjects() throws NotExistException{
        List<Project> projectList = repository.findAll();
        if (!projectList.isEmpty()){
            return projectList;
        } else {
            throw new NotExistException("0 project record");
        }
    }

    public Project getProject(int projectId) throws NotExistException {
        Project project = repository.findByProjectId(projectId);
        if(project != null){
            return project;
        } else {
            throw new NotExistException("Project not found with id: " + projectId);
        }
    }

    public Project updateProject(int projectId, ProjectRequest request) throws NotExistException{
        Project project = getProject(projectId);       // get department to be updated
        project.setName(request.getName());            // updating
        return repository.save(project);
    }

    public void deleteProjectById(int projectId) throws NotExistException {
        Project project = getProject(projectId);
        repository.deleteById(project.getProjectId());
    }

    public void deleteAllProjects(){
        repository.deleteAll();
    }
}
