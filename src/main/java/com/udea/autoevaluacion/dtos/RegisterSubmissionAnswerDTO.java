package com.udea.autoevaluacion.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionAnswerDTO {
    @NotBlank(message = "El número de pregunta no puede ser vacío")
    private int questionNumber;

    @NotBlank(message = "El nivel actual no puede ser vacío")
    private int actualLevel;

    @NotBlank(message = "El nivel deseado no puede ser vacío")
    private int targetLevel;
}