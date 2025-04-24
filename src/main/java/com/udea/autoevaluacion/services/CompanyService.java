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
                .map(company -> CompanyDTO.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .organizationType(company.getOrganizationType())
                        .organizationSector(company.getOrganizationSector())
                        .country(company.getCountry())
                        .numberOfEmployees(company.getNumberOfEmployees())
                        .build())
                .collect(Collectors.toList());
    }
}