package com.udea.autoevaluacion.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionPartDTO {
    @NotBlank(message = "El número de parte del formulario no puede ser vacío")
    private int partNumber;

    @NotBlank(message = "Las preguntas de la parte no pueden estar vacias")
    private List<RegisterSubmissionAnswerDTO> registerSubmissionAnswers;

    @NotBlank(message = "Las métricas no pueden estar vacías")
    private RegisterSubmissionPartMetricsDTO registerSubmissionPartMetrics;
}