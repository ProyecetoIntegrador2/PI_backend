package com.udea.autoevaluacion.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionAnswerDTO {
    private int questionNumber;
    private String questionText;
    private int actualLevel;
    private int targetLevel;
}
