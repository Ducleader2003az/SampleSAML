package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.models.Company;
import com.kokobato.huynhduc.kokobatodemo.models.Floor;
import com.kokobato.huynhduc.kokobatodemo.repositories.CompanyRepository;
import com.kokobato.huynhduc.kokobatodemo.repositories.specification.CompanySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    private CompanySpecification companySpecification;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Company> getCompaniesByOfficeId(int officeId) {
        Specification<Company> spec = companySpecification.findCompaniesByOfficeId(officeId);

        return companyRepository.findAll(spec);
    }

    public List<Company> getCompaniesByFloorId(int floorId) {
        Specification<Company> spec = companySpecification.findCompaniesByFloorId(floorId);

        return companyRepository.findAll(spec);
    }
}
