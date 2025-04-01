package com.udea.autoevaluacion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.LoginDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.UserRepository;
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO login(LoginDTO loginDTO) throws Exception{
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setCompanyName(user.getCompanyName());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
