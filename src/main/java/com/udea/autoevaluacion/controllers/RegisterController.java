package com.udea.autoevaluacion.controllers;

import com.udea.autoevaluacion.constants.EndpointsConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udea.autoevaluacion.dtos.LoginDTO;

import com.udea.autoevaluacion.services.LoginService;
import com.udea.autoevaluacion.services.RegisterService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping(EndpointsConstants.REGISTER_URL)
@CrossOrigin(origins = "${frontend.origin}")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping()
    public ResponseEntity<String> register(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        return new ResponseEntity<>(registerService.register(loginDTO), HttpStatus.ACCEPTED);
    }
}
