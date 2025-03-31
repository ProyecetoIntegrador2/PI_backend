package com.udea.autoevaluacion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.RegisterDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.UserRepository;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO register(RegisterDTO registerDTO) {
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        UserDTO userDTO = new UserDTO();

        User user = userRepository.save(User.builder()
                .email(registerDTO.getEmail())
                .address(registerDTO.getAddress())
                .companyName(registerDTO.getCompanyName())
                .password(encodedPassword)
                .build());

        userDTO.setId(user.getId());
        userDTO.setCompanyName(user.getCompanyName());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
