package com.udea.autoevaluacion.controllers;

import com.udea.autoevaluacion.constants.EndpointsConstants;
import com.udea.autoevaluacion.dtos.LoginDTO;
import com.udea.autoevaluacion.dtos.AuthResponseDTO;
import com.udea.autoevaluacion.services.LoginService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(EndpointsConstants.LOGIN_URL)
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            AuthResponseDTO response = loginService.login(loginDTO);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (UsernameNotFoundException | IllegalArgumentException e) {
            System.err.println("Autenticacion fallida " + loginDTO.getEmail() + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.err.println("Error inesperado con el email: " + loginDTO.getEmail() + ": "
                    + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}