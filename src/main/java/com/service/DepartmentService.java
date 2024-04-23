package com.service;

import com.dto.DepartmentRequest;
import com.exception.NotExistException;
import com.model.Department;
import com.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public Department addDepartment(DepartmentRequest request){
        Department department = Department.build(0, request.getName());
        return repository.save(department);
    }

    public List<Department> getAllDepartment() throws NotExistException {
        List<Department> deptList = repository.findAll();
        if (!deptList.isEmpty()){
            return deptList;
        } else {
            throw new NotExistException("0 department record");
        }
    }

    public Department getDepartment(int deptId) throws NotExistException {
        Department dept = repository.findByDeptId(deptId);
        if(dept != null){
            return dept;
        } else {
            throw new NotExistException("Department not found with id: " + deptId);
        }
    }

    public Department updateDepartment(int deptId, DepartmentRequest request) throws NotExistException {
        Department dept = getDepartment(deptId);    // get department to be updated
        dept.setName(request.getName());            // updating
        return repository.save(dept);
    }

    public void deleteDepartmentById(int deptId) throws NotExistException {
        Department dept = getDepartment(deptId);
        repository.deleteById(dept.getDeptId());
    }

    public void deleteAllDepartments(){
        repository.deleteAll();
    }
}
