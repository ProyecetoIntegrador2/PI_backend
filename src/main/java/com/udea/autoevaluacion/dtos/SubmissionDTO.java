package com.udea.autoevaluacion.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionDTO {
    private String userId;
    private LocalDateTime submissionDate;
    private List<SubmissionPartDTO> submissionParts;
}
