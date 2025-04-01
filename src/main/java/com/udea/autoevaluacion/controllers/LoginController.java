package com.udea.autoevaluacion.controllers;

import com.udea.autoevaluacion.constants.EndpointsConstants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udea.autoevaluacion.dtos.LoginDTO;
import com.udea.autoevaluacion.dtos.UserDTO;
import com.udea.autoevaluacion.services.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(EndpointsConstants.LOGIN_URL)
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        return new ResponseEntity<>(loginService.login(loginDTO), HttpStatus.ACCEPTED);
    }
}
