package com.udea.autoevaluacion.controllers;

import com.udea.autoevaluacion.constants.EndpointsConstants;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udea.autoevaluacion.dtos.RegisterDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.services.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(EndpointsConstants.REGISTER_URL)
@CrossOrigin(origins = "*")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(registerService.register(registerDTO), HttpStatus.CREATED);
    }
}
