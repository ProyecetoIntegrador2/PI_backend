package com.udea.autoevaluacion.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionPartDTO {
    private Long submissionId;
    private int partNumber;
    private List<SubmissionAnswerDTO> submissionAnswers;
    private SubmissionMetricsDTO submissionMetrics;
}
