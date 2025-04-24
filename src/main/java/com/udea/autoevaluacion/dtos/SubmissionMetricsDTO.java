package com.udea.autoevaluacion.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionMetricsDTO {
    private String submissionPartId;
    private int averageActualScore;
    private int averageDesiredScore;
    private BigDecimal qualifiedMajorityCriterion;
    private Integer majorityCutOffLevel;
    private BigDecimal thresholdBasedScoring;
}
