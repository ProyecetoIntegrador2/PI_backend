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
import com.udea.autoevaluacion.mappers.SubmissionMapper;
import com.udea.autoevaluacion.models.AnswerOptionDefinition;
import com.udea.autoevaluacion.models.FormDefinition;
import com.udea.autoevaluacion.models.PartDefinition;
import com.udea.autoevaluacion.models.QuestionDefinition;
import com.udea.autoevaluacion.models.Submission;
import com.udea.autoevaluacion.models.SubmissionAnswer;
import com.udea.autoevaluacion.models.SubmissionMetrics;
import com.udea.autoevaluacion.models.SubmissionPart;
import com.udea.autoevaluacion.models.SubmissionPartMetrics;
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
    private final MetricsService metricsService;
    private final SubmissionMapper submissionMapper;

    public SubmissionService(SubmissionRepository submissionRepository,
            SubmissionPartRepository submissionPartRepository,
            SubmissionAnswerRepository submissionAnswerRepository,
            SubmissionPartMetricsRepository submissionPartMetricsRepository,
            UserRepository userRepository, FormDefinitionRepository formDefinitionRepository, MetricsService metricsService, SubmissionMapper submissionMapper) {
        this.submissionRepository = submissionRepository;
        this.submissionPartRepository = submissionPartRepository;
        this.submissionAnswerRepository = submissionAnswerRepository;
        this.submissionPartMetricsRepository = submissionPartMetricsRepository;
        this.formDefinitionRepository = formDefinitionRepository;
        this.userRepository = userRepository;
        this.metricsService = metricsService;
        this.submissionMapper = submissionMapper;
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
        List<PartDefinition> partDefinitions = formDefinition.getPartDefinitions();
        
        List<SubmissionPart> submissionParts = createSubmissionParts(submission, registerSubmissionPartsDTO, partDefinitions);

        submission.setFormDefinition(formDefinition);
        submission.setSubmissionParts(submissionParts);
        submission.setUser(user);
        submission.setFormDefinition(formDefinition);

        SubmissionMetrics submissionMetrics = calculateSubmissionMetrics(submission, submissionParts);
        submission.setSubmissionMetrics(submissionMetrics);

        submissionRepository.save(submission);
        SubmissionDTO submissionDTO = submissionMapper.toSubmissionDTO(submission);

        return submissionDTO;
    }

    private List<SubmissionPart> createSubmissionParts(Submission submission, List<RegisterSubmissionPartDTO> registerSubmissionPartsDTO, List<PartDefinition> partDefinitions) {
        List<SubmissionPart> submissionParts = new ArrayList<>();
        for (RegisterSubmissionPartDTO registerSubmissionPartDTO : registerSubmissionPartsDTO) {
            SubmissionPart submissionPart = new SubmissionPart();
            
            int partNumber = registerSubmissionPartDTO.getPartNumber();
            PartDefinition partDefinition = partDefinitions.stream()
                    .filter(part -> part.getPartNumber() == partNumber)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Parte del formulario no encontrada"));

            List<RegisterSubmissionAnswerDTO> registerSubmissionAnswersDTO = registerSubmissionPartDTO.getRegisterSubmissionAnswers();
            List<SubmissionAnswer> submissionAnswers = createSubmissionAnswers(submissionPart, partDefinition, registerSubmissionAnswersDTO);

            SubmissionPartMetrics submissionPartMetrics = calculateSubmissionPartMetrics(submissionAnswers, submissionPart);
                
            submissionPart.setPartDefinition(partDefinition);
            submissionPart.setSubmission(submission);
            submissionPart.setSubmissionAnswers(submissionAnswers);
            submissionPart.setSubmissionPartMetrics(submissionPartMetrics);

            submissionParts.add(submissionPart);
        }
        return submissionParts;
    }

    private SubmissionPartMetrics calculateSubmissionPartMetrics(List<SubmissionAnswer> submissionAnswers, SubmissionPart submissionPart) {
        int totalDesired = submissionAnswers.stream()
                .mapToInt(submissionAnswer -> submissionAnswer.getTargetOption().getOptionLevel())
                .sum();
        int totalActual = submissionAnswers.stream()
                .mapToInt(submissionAnswer -> submissionAnswer.getActualOption().getOptionLevel())
                .sum();
        
        int averageActualScore = metricsService.calculateAverageActualScore(totalActual, submissionAnswers.size());
        int averageDesiredScore = metricsService.calculateAverageDesiredScore(totalDesired, submissionAnswers.size());

        SubmissionPartMetrics submissionPartMetrics = new SubmissionPartMetrics();
        submissionPartMetrics.setSubmissionPart(submissionPart);
        submissionPartMetrics.setAverageActualScore(averageActualScore);
        submissionPartMetrics.setAverageDesiredScore(averageDesiredScore);
        
        return submissionPartMetrics;
    }

    private List<SubmissionAnswer> createSubmissionAnswers(SubmissionPart submissionPart, PartDefinition partDefinition, List<RegisterSubmissionAnswerDTO> registerSubmissionAnswersDTO) {
        List<SubmissionAnswer> submissionAnswers = registerSubmissionAnswersDTO.stream()
                        .map(answer -> {
                            SubmissionAnswer submissionAnswer = new SubmissionAnswer();
                            QuestionDefinition questionDefinition = partDefinition.getQuestionDefinitions().stream()
                                    .filter(question -> question.getQuestionNumber() == answer.getQuestionNumber())
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Definicion de la pregunta no encontrada"));
                            
                            AnswerOptionDefinition actualOption = questionDefinition.getAnswerOptions().stream()
                                    .filter(option -> option.getOptionLevel() == answer.getActualLevel())
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Opcion de respuesta no encontrada"));
                            
                            AnswerOptionDefinition desiredOption = questionDefinition.getAnswerOptions().stream()
                                    .filter(option -> option.getOptionLevel() == answer.getTargetLevel())
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Opcion de respuesta no encontrada"));

                            submissionAnswer.setQuestionDefinition(questionDefinition);
                            submissionAnswer.setActualOption(actualOption);
                            submissionAnswer.setTargetOption(desiredOption);
                            submissionAnswer.setSubmissionPart(submissionPart);
                            
                            return submissionAnswer;
                        })
                        .collect(Collectors.toList());
        return submissionAnswers;
    }

    public SubmissionMetrics calculateSubmissionMetrics(Submission submission, List<SubmissionPart> submissionParts) {
        SubmissionMetrics submissionMetrics = new SubmissionMetrics();
        int totalDesired = submissionParts.stream()
                .flatMap(part -> part.getSubmissionAnswers().stream())
                .mapToInt(submissionAnswer -> submissionAnswer.getTargetOption().getOptionLevel())
                .sum();
        int totalActual = submissionParts.stream()
                .flatMap(part -> part.getSubmissionAnswers().stream())
                .mapToInt(submissionAnswer -> submissionAnswer.getActualOption().getOptionLevel())
                .sum();
        
        int totalCountQuestions = submissionParts.stream()
                .mapToInt(part -> part.getSubmissionAnswers().size())
                .sum();

        int averageActualScore = metricsService.calculateAverageActualScore(totalActual, totalCountQuestions);
        int averageDesiredScore = metricsService.calculateAverageDesiredScore(totalDesired, totalCountQuestions);

        submissionMetrics.setSubmission(submission);
        submissionMetrics.setAverageActualScore(averageActualScore);
        submissionMetrics.setAverageDesiredScore(averageDesiredScore);
        
        return submissionMetrics;
    }

    @Transactional
    public List<SubmissionDTO> getSubmissionsByUserId(Long userId) {
        List<Submission> submissions = submissionRepository.findAllByUser_Id(userId);
        return submissionMapper.toSubmissionDTOs(submissions);
    }
}
