package com.udea.autoevaluacion.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.RegisterSubmissionAnswerDTO;
import com.udea.autoevaluacion.dtos.RegisterSubmissionDTO;
import com.udea.autoevaluacion.dtos.RegisterSubmissionPartDTO;
import com.udea.autoevaluacion.dtos.SubmissionDTO;
import com.udea.autoevaluacion.models.FormDefinition;
import com.udea.autoevaluacion.models.Submission;
import com.udea.autoevaluacion.models.SubmissionAnswer;
import com.udea.autoevaluacion.models.SubmissionPart;
import com.udea.autoevaluacion.models.User;
import com.udea.autoevaluacion.repositories.FormDefinitionRepository;
import com.udea.autoevaluacion.repositories.SubmissionAnswerRepository;
import com.udea.autoevaluacion.repositories.SubmissionPartMetricsRepository;
import com.udea.autoevaluacion.repositories.SubmissionPartRepository;
import com.udea.autoevaluacion.repositories.SubmissionRepository;
import com.udea.autoevaluacion.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionPartRepository submissionPartRepository;
    private final SubmissionAnswerRepository submissionAnswerRepository;
    private final SubmissionPartMetricsRepository submissionPartMetricsRepository;
    private final FormDefinitionRepository formDefinitionRepository;
    private final UserRepository userRepository;

    public SubmissionService(SubmissionRepository submissionRepository,
            SubmissionPartRepository submissionPartRepository,
            SubmissionAnswerRepository submissionAnswerRepository,
            SubmissionPartMetricsRepository submissionPartMetricsRepository,
            UserRepository userRepository, FormDefinitionRepository formDefinitionRepository) {
        this.submissionRepository = submissionRepository;
        this.submissionPartRepository = submissionPartRepository;
        this.submissionAnswerRepository = submissionAnswerRepository;
        this.submissionPartMetricsRepository = submissionPartMetricsRepository;
        this.formDefinitionRepository = formDefinitionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SubmissionDTO createSubmission(RegisterSubmissionDTO registerSubmissionDTO) {
        Submission submission = new Submission();
        submission.setSubmissionDate(LocalDateTime.now());

        User user = userRepository.findById(registerSubmissionDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        FormDefinition formDefinition = formDefinitionRepository
                .findById(registerSubmissionDTO.getFormDefinitionId())
                .orElseThrow(() -> new RuntimeException("Definicion del formulario no encontrada"));

        List<RegisterSubmissionPartDTO> registerSubmissionPartsDTO = registerSubmissionDTO.getRegisterSubmissionParts();
        List<SubmissionPart> submissionParts = new ArrayList<>();
        for (RegisterSubmissionPartDTO registerSubmissionPartDTO : registerSubmissionPartsDTO) {
            SubmissionPart submissionPart = new SubmissionPart();

            List<RegisterSubmissionAnswerDTO> registerSubmissionAnswersDTO = registerSubmissionPartDTO.getRegisterSubmissionAnswers();
            List<SubmissionAnswer> submissionAnswers = registerSubmissionAnswersDTO.stream()
                            .map(answer -> SubmissionAnswer.builder()
                            .questionDefinition(null)
                            .submissionPart(submissionPart)
                            .build())
                            .collect(Collectors.toList());
            
            submissionPart.setPartDefinition(null);
            submissionPart.setSubmission(submission);
            submissionPart.setSubmissionAnswers(submissionAnswers);
            submissionPart.setSubmissionPartMetrics(null);

            submissionParts.add(submissionPart);
        }
        submission.setSubmissionParts(submissionParts);
        submission.setUser(user);
        submission.setFormDefinition(formDefinition);

        submissionRepository.save(submission);

        return null;
    }
}
