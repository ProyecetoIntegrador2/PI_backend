package com.udea.autoevaluacion.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionDTO {
    private Long userId;
    private String formName;
    private String formVersion;
    private LocalDateTime submissionDate;
    private List<SubmissionPartDTO> submissionParts;
    private SubmissionMetricsDTO submissionMetrics;
}
