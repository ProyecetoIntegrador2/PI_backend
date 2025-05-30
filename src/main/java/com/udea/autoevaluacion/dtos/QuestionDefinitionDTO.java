package com.udea.autoevaluacion.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDefinitionDTO {
    private int questionNumber;
    private String questionText;
    private List<AnswerOptionDefinitionDTO> answerOptions;
}
