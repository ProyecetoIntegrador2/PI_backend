package com.udea.autoevaluacion.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.autoevaluacion.dtos.LoginDTO;
import com.udea.autoevaluacion.dtos.AuthResponseDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.UserRepository;
import com.udea.autoevaluacion.security.JwtUtil;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = false)
    public AuthResponseDTO login(LoginDTO loginDTO) throws UsernameNotFoundException, IllegalArgumentException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (UsernameNotFoundException | org.springframework.security.authentication.BadCredentialsException e) {
            throw new IllegalArgumentException("Contraseña o correo invalido.");
        }

        String email = authentication.getName();
        String token = jwtUtil.generateToken(email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Email de usuario no encontrado: " + email));

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jobTitle(user.getJobTitle())
                .companyName(user.getCompany().getName())
                .yearsOfExperienceTechnology(user.getYearsOfExperienceTechnology())
                .build();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return AuthResponseDTO.builder()
                .token(token)
                .user(userDTO)
                .roles(roles)
                .build();
    }
}