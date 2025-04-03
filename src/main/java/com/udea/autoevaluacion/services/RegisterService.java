package com.udea.autoevaluacion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.RegisterDTO;
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

    public UserDTO register(RegisterDTO registerDTO) {;
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());

        Company company = companyRepository.findById(registerDTO.getCompanyId()).orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        User user = userRepository.save(User.builder()
                .firstName(registerDTO.getFirstName())
                .lastName(registerDTO.getLastName())
                .email(registerDTO.getEmail())
                .password(encodedPassword)
                .jobTitle(registerDTO.getJobTitle())
                .yearsOfExperienceTechnology(registerDTO.getYearsOfExperienceTechnology())
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
}
