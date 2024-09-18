package com.kokobato.huynhduc.kokobatodemo.controller;

import com.kokobato.huynhduc.kokobatodemo.dtos.EmployeeSearchDTO;
import com.kokobato.huynhduc.kokobatodemo.models.Employee;
import com.kokobato.huynhduc.kokobatodemo.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/list")
    public ResponseEntity<?> getAll() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/employee/search")
    public ResponseEntity<?> search(@RequestBody EmployeeSearchDTO employeeSearchDTO) {
        try {
            List<Employee> employees = employeeService.searchEmployees(employeeSearchDTO);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
