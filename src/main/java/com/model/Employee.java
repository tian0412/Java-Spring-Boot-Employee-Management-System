package com.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empId;
    private String name;
    private String position;
    private int deptId;

    @ManyToMany
    @JoinTable(name = "employee_project",
        joinColumns = @JoinColumn(name="empId"),
        inverseJoinColumns = @JoinColumn(name="projectId"))
    private Set<Project> assignedProjects = new HashSet<>();

    public Employee(String name, String position, int deptId){
        this.name = name;
        this.position = position;
        this.deptId = deptId;
    }
}
