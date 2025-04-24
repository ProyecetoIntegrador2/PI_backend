package com.udea.autoevaluacion.services;

import org.springframework.stereotype.Service;

import com.udea.autoevaluacion.dtos.SubmissionDTO;
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
    private final UserRepository userRepository;

    public SubmissionService(SubmissionRepository submissionRepository,
            SubmissionPartRepository submissionPartRepository,
            SubmissionAnswerRepository submissionAnswerRepository,
            SubmissionMetricsRepository submissionMetricsRepository,
            UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.submissionPartRepository = submissionPartRepository;
        this.submissionAnswerRepository = submissionAnswerRepository;
        this.submissionMetricsRepository = submissionMetricsRepository;
        this.userRepository = userRepository;
    }

    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO) {
        

        return submissionDTO;
    }
}
