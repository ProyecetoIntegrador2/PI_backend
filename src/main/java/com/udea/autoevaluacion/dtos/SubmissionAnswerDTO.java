package com.udea.autoevaluacion.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmissionAnswerDTO {
    private QuestionDefinitionDTO questionDefinitionDTO;
    private int actualLevel;
    private int targetLevel;
}
