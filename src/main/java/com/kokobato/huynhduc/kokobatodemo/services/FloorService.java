package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.models.Floor;
import com.kokobato.huynhduc.kokobatodemo.repositories.FloorRepository;
import com.kokobato.huynhduc.kokobatodemo.repositories.OfficeRepository;
import com.kokobato.huynhduc.kokobatodemo.repositories.specification.FloorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {
    @Autowired
    private FloorRepository floorRepository;

    private FloorSpecification floorSpecification;

    public List<Floor> getFloorsByOfficeId(int officeId) {
        Specification<Floor> spec = floorSpecification.findFloorsByOfficeId(officeId);

        return floorRepository.findAll(spec);
    }

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }
}
