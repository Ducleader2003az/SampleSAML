package com.kokobato.huynhduc.kokobatodemo.controller;

import com.kokobato.huynhduc.kokobatodemo.models.Floor;
import com.kokobato.huynhduc.kokobatodemo.services.FloorService;
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
public class FloorController {
    @Autowired
    private FloorService floorService;

    @GetMapping("/floor/findByOfficeId")
    public ResponseEntity<?> findByOfficeId(@RequestParam int officeId) {
        try {
            List<Floor> floors = floorService.getFloorsByOfficeId(officeId);
            return new ResponseEntity<>(floors, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/floor/list")
    public ResponseEntity<?> getAll() {
        try {
            List<Floor> floors = floorService.getAllFloors();
            return new ResponseEntity<>(floors, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
