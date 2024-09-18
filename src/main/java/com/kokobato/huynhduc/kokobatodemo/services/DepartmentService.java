package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.models.Department;
import com.kokobato.huynhduc.kokobatodemo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> getDepartmentsByParent(int parentId) {
        return departmentRepository.findAllByDpmParent(parentId);
    }
}
