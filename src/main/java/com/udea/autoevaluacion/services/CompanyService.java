package com.udea.autoevaluacion.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.autoevaluacion.dtos.CompanyDTO;
import com.udea.autoevaluacion.repositories.CompanyRepository;


@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(company -> new CompanyDTO(company.getId(), company.getName(), company.getOrganizationType(), company.getOrganizationSector(), company.getCountry(), company.getNumberOfEmployees()))
                .collect(Collectors.toList());
    }
}
