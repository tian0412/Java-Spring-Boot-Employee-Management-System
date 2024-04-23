package com.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Department")
@Setter
@Getter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deptId;
    private String name;
}
