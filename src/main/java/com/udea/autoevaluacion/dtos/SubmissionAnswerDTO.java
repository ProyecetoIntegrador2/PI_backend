package com.udea.autoevaluacion.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionAnswerDTO {
    private String questionNumber;
    private String questionName;
    private int actualLevel;
    private int targetLevel;
}
