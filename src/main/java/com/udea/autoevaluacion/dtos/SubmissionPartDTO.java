package com.udea.autoevaluacion.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionPartDTO {
    private int partNumber;
    private String partName;
    private List<SubmissionAnswerDTO> submissionAnswers;
    private SubmissionPartMetricsDTO submissionPartMetrics;
}
