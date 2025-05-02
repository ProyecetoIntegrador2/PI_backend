package com.udea.autoevaluacion.dtos;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionAnswerDTO {
    @Positive(message = "El número de pregunta debe ser positivo")
    private int questionNumber;

    @Positive(message = "El nivel actual debe ser positivo")
    private int actualLevel;

    @Positive(message = "El nivel objetivo debe ser positivo")
    private int targetLevel;
}