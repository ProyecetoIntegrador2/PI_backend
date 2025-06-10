package com.udea.autoevaluacion.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionPartMetricsDTO {
    private int averageActualScore;
    private int averageDesiredScore;
    private int majorityCutOffLevel;
    private int thresholdBasedScoring;
}
