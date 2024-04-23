package com.service;

import com.dto.EmployeeRequest;
import com.exception.NotExistException;
import com.model.Employee;
import com.model.Project;
import com.repository.EmployeeRepository;
import com.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Employee addEmployee(EmployeeRequest request){
        Employee employee = new Employee(
                request.getName(),
                request.getPosition(),
                request.getDeptId());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() throws NotExistException{
        List<Employee> empList = employeeRepository.findAll();
        if (!empList.isEmpty()){
            return empList;
        } else {
            throw new NotExistException("0 employee record");
        }
    }

    public Employee getEmployee(int empId) throws NotExistException {
        Employee emp = employeeRepository.findByEmpId(empId);
        if(emp != null){
            return emp;
        } else {
            throw new NotExistException("Employee not found with id: " + empId);
        }
    }

    public Employee updateEmployee(int empId, EmployeeRequest request) throws NotExistException{
        Employee emp = getEmployee(empId);
        emp.setName(request.getName());
        emp.setPosition(request.getPosition());
        emp.setDeptId(request.getDeptId());
        return employeeRepository.save(emp);
    }

    public Employee assignProjectToEmployee(int empId, int projectId){
        Set<Project> projectSet = null;
        Employee employee = employeeRepository.findByEmpId(empId);
        Project project = projectRepository.findByProjectId(projectId);
        projectSet = employee.getAssignedProjects();
        projectSet.add(project);
        employee.setAssignedProjects(projectSet);
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(int empId) throws NotExistException{
        Employee emp = getEmployee(empId);
        employeeRepository.deleteById(emp.getEmpId());
    }

    public void deleteAllEmployee() { employeeRepository.deleteAll(); }
}
