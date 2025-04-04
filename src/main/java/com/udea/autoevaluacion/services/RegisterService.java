package com.udea.autoevaluacion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.CompanyDTO;
import com.udea.autoevaluacion.dtos.RegisterUserDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.models.Company;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.CompanyRepository;
import com.udea.autoevaluacion.repositories.UserRepository;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    public UserDTO registerUser(RegisterUserDTO registerUserDTO) {;
        String encodedPassword = passwordEncoder.encode(registerUserDTO.getPassword());

        Company company = companyRepository.findById(registerUserDTO.getCompanyId()).orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        User user = userRepository.save(User.builder()
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .email(registerUserDTO.getEmail())
                .password(encodedPassword)
                .jobTitle(registerUserDTO.getJobTitle())
                .yearsOfExperienceTechnology(registerUserDTO.getYearsOfExperienceTechnology())
                .company(company)
                .build());

        UserDTO userDTO = UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jobTitle(user.getJobTitle())
                .companyName(company.getName())
                .yearsOfExperienceTechnology(user.getYearsOfExperienceTechnology())
                .build();

        return userDTO;
    }

    public CompanyDTO registerCompany(CompanyDTO companyDTO) {
        Company company = companyRepository.save(Company.builder()
                .name(companyDTO.getName())
                .address(companyDTO.getAddress())
                .phoneNumber(companyDTO.getPhoneNumber())
                .build());

        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .build();

    }
}