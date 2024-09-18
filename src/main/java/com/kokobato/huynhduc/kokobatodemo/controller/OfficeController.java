package com.kokobato.huynhduc.kokobatodemo.controller;

import com.kokobato.huynhduc.kokobatodemo.models.Office;
import com.kokobato.huynhduc.kokobatodemo.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @GetMapping("/office/list")
    public ResponseEntity<?> getAllOffices() {
        try {
            List<Office> offices = officeService.getAllOffices();
            return new ResponseEntity<>(offices, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
