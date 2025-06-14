package com.udea.autoevaluacion.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionMetricsDTO {
    private int averageActualScore;
    private int averageDesiredScore;
    private int majorityCutOffLevel;
    private int thresholdBasedScoring;
}
