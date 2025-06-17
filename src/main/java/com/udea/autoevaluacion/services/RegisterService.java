package com.udea.autoevaluacion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.CompanyDTO;
import com.udea.autoevaluacion.dtos.RegisterCompanyDTO;
import com.udea.autoevaluacion.dtos.RegisterUserDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.models.Company;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.CompanyRepository;
import com.udea.autoevaluacion.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public UserDTO registerUser(RegisterUserDTO registerUserDTO) {
        String encodedPassword = passwordEncoder.encode(registerUserDTO.getPassword());

        Company company = companyRepository.findById(registerUserDTO.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        User user = userRepository.save(User.builder()
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .email(registerUserDTO.getEmail())
                .password(encodedPassword)
                .jobTitle(registerUserDTO.getJobTitle())
                .yearsOfExperienceTechnology(registerUserDTO.getYearsOfExperienceTechnology())
                .company(company)
                .roles(Set.of("ROLE_USER"))
                .build());

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jobTitle(user.getJobTitle())
                .companyName(company.getName())
                .yearsOfExperienceTechnology(user.getYearsOfExperienceTechnology())
                .build();

        return userDTO;
    }

    @Transactional
    public CompanyDTO registerCompany(RegisterCompanyDTO registerCompanyDTO) {
        Company company = companyRepository.save(Company.builder()
                .name(registerCompanyDTO.getName())
                .organizationType(registerCompanyDTO.getOrganizationType())
                .organizationSector(registerCompanyDTO.getOrganizationSector())
                .country(registerCompanyDTO.getCountry())
                .numberOfEmployees(registerCompanyDTO.getNumberOfEmployees())
                .build());

        CompanyDTO companyDTO = CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .organizationType(company.getOrganizationType())
                .organizationSector(company.getOrganizationSector())
                .country(company.getCountry())
                .numberOfEmployees(company.getNumberOfEmployees())
                .build();

        return companyDTO;

    }
}