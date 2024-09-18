package com.kokobato.huynhduc.kokobatodemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "emp_cd", nullable = false, length = 50)
    private String empCode;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    @Column(name = "emp_email", nullable = false, length = 100)
    private String empDesc;

    @Column(name = "emp_phone", nullable = false, length = 15)
    private String empPhone;

    @Column(name = "emp_status", nullable = true, length = 15)
    private String empStatus;

    @Column(name = "emp_position", nullable = false, length = 50)
    private String empPosition;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
