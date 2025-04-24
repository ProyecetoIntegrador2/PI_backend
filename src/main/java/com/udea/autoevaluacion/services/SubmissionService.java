package com.udea.autoevaluacion.services;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.RegisterSubmissionDTO;
import com.udea.autoevaluacion.dtos.SubmissionDTO;
import com.udea.autoevaluacion.models.FormDefinition;
import com.udea.autoevaluacion.models.Submission;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.FormDefinitionRepository;
import com.udea.autoevaluacion.repositories.SubmissionAnswerRepository;
import com.udea.autoevaluacion.repositories.SubmissionMetricsRepository;
import com.udea.autoevaluacion.repositories.SubmissionPartRepository;
import com.udea.autoevaluacion.repositories.SubmissionRepository;
import com.udea.autoevaluacion.repositories.UserRepository;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionPartRepository submissionPartRepository;
    private final SubmissionAnswerRepository submissionAnswerRepository;
    private final SubmissionMetricsRepository submissionMetricsRepository;
    private final FormDefinitionRepository formDefinitionRepository;
    private final UserRepository userRepository;

    public SubmissionService(SubmissionRepository submissionRepository,
            SubmissionPartRepository submissionPartRepository,
            SubmissionAnswerRepository submissionAnswerRepository,
            SubmissionMetricsRepository submissionMetricsRepository,
            UserRepository userRepository, FormDefinitionRepository formDefinitionRepository) {
        this.submissionRepository = submissionRepository;
        this.submissionPartRepository = submissionPartRepository;
        this.submissionAnswerRepository = submissionAnswerRepository;
        this.submissionMetricsRepository = submissionMetricsRepository;
        this.formDefinitionRepository = formDefinitionRepository;
        this.userRepository = userRepository;
    }

    public SubmissionDTO createSubmission(RegisterSubmissionDTO registerSubmissionDTO) {
        Submission submission = new Submission();

        User user = userRepository.findById(registerSubmissionDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        FormDefinition formDefinition = formDefinitionRepository
                .findById(registerSubmissionDTO.getFormDefinitionId())
                .orElseThrow(() -> new RuntimeException("Definicion del formulario no encontrada"));

        LocalDateTime submissionDate = LocalDateTime.now();

        

        return null;
    }
}
