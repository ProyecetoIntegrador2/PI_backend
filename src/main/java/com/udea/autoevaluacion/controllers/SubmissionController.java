package com.udea.autoevaluacion.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udea.autoevaluacion.constants.EndpointsConstants;
import com.udea.autoevaluacion.dtos.RegisterSubmissionDTO;
import com.udea.autoevaluacion.dtos.SubmissionDTO;
import com.udea.autoevaluacion.services.SubmissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(EndpointsConstants.SUBMISSION_URL)
@CrossOrigin(origins = "*")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubmissionDTO> createSubmission(@Valid @RequestBody RegisterSubmissionDTO registerSubmissionDTO) {
        return new ResponseEntity<>(submissionService.createSubmission(registerSubmissionDTO), HttpStatus.CREATED); 
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubmissionDTO>> getSubmission(@RequestParam Long userId) {
        return new ResponseEntity<>(submissionService.getSubmissionsByUserId(userId), HttpStatus.OK);
    }
}