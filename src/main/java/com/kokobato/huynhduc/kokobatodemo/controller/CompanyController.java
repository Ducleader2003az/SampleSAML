package com.kokobato.huynhduc.kokobatodemo.controller;

import com.kokobato.huynhduc.kokobatodemo.models.Company;
import com.kokobato.huynhduc.kokobatodemo.services.CompanyService;
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
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/company/list")
    public ResponseEntity<?> getAllCompanies() {
        try {
            List<Company> companies = companyService.getAllCompanies();
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/company/findByOfficeId")
    public ResponseEntity<?> findByOfficeId(@RequestParam(required = false, defaultValue = "0") int officeId) {
        try {
            List<Company> companies = companyService.getCompaniesByOfficeId(officeId);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/company/findByFloorId")
    public ResponseEntity<?> findByFloorId(@RequestParam(required = false, defaultValue = "0") int floorId) {
        try {
            List<Company> companies = companyService.getCompaniesByFloorId(floorId);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
