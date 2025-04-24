package com.udea.autoevaluacion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.LoginDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public UserDTO login(LoginDTO loginDTO) throws Exception{
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jobTitle(user.getJobTitle())
                .companyName(user.getCompany().getName())
                .yearsOfExperienceTechnology(user.getYearsOfExperienceTechnology())
                .build();

        return userDTO;
    }
}
