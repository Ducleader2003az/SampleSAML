package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.dtos.EmployeeSearchDTO;
import com.kokobato.huynhduc.kokobatodemo.models.Employee;
import com.kokobato.huynhduc.kokobatodemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> searchEmployees(EmployeeSearchDTO employeeSearchDTO) {
        List<Employee> employeeList = employeeRepository.seacrhEmployees(
                employeeSearchDTO.getDepartmentId(),
                employeeSearchDTO.getLocationStatus(),
                employeeSearchDTO.getName());
        return employeeList;
    }
}
