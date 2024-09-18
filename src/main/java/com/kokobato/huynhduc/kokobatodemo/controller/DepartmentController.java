package com.kokobato.huynhduc.kokobatodemo.controller;

import com.kokobato.huynhduc.kokobatodemo.models.Department;
import com.kokobato.huynhduc.kokobatodemo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/department/list")
    public ResponseEntity<?> getAllDepartments() {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/department/findAllByDpmParent")
    public ResponseEntity<?> getDepartmentsByDpmParent(@RequestParam int dpmParenId) {
        try {
            List<Department> departments = departmentService.getDepartmentsByParent(dpmParenId);
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
