package com.udea.autoevaluacion.controllers;

import com.udea.autoevaluacion.constants.EndpointsConstants;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.autoevaluacion.services.LoginService;
import com.udea.autoevaluacion.services.RegisterService;

@RestController
@RequestMapping(EndpointsConstants.AUTH_URL)
@CrossOrigin(origins = "${frontend.origin}")
public class AuthController {
    private final LoginService loginService;
    private final RegisterService registerService;

    public AuthController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }
}

