package com.udea.autoevaluacion.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormQuestionsDTO {
    @NotBlank(message = "El número de pregunta no puede ser vacío")
    private int questionNumber;

    @NotBlank(message = "El texto de la pregunta no puede ser vacío")
    private String questionText;

    @NotBlank(message = "El nivel actual no puede ser vacío")
    private int actualLevel;

    @NotBlank(message = "El nivel deseado no puede ser vacío")
    private int targetLevel;
}
