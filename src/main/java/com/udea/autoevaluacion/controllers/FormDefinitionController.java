package com.udea.autoevaluacion.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.autoevaluacion.constants.EndpointsConstants;
import com.udea.autoevaluacion.dtos.FormDefinitionDTO;
import com.udea.autoevaluacion.services.FormDefinitionService;

@RestController
@RequestMapping(EndpointsConstants.FORM_DEFINITION_URL)
@CrossOrigin(origins = "*")
public class FormDefinitionController {
    private final FormDefinitionService formDefinitionService;

    public FormDefinitionController(FormDefinitionService formDefinitionService) {
        this.formDefinitionService = formDefinitionService;
    }

    @GetMapping(value = "/latest/{formName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormDefinitionDTO> getLatestFormDefinition(@PathVariable String formName) {
        FormDefinitionDTO latestFormDefinition = formDefinitionService.getLatestFormDefinition(formName);
        return new ResponseEntity<>(latestFormDefinition, HttpStatus.OK);
    }
}
