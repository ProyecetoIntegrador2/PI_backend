package com.udea.autoevaluacion.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionPartMetricsDTO {
    @NotBlank(message = "El ID de la parte del formulario no puede ser vacío")
    private String registerSubmissionPartId;

    @NotBlank(message = "La metrica promedio del puntaje actual no puede ser vacía")
    private int averageActualScore;

    @NotBlank(message = "La metrica promedio del puntaje deseado no puede ser vacía")
    private int averageDesiredScore;

    @NotBlank(message = "El criterio de mayoría calificada no puede ser vacío")
    private BigDecimal qualifiedMajorityCriterion;

    @NotBlank(message = "El punto de corte mayoritario no puede ser vacío")
    private Integer majorityCutOffLevel;

    @NotBlank(message = "El puntaje basado en umbral no puede ser vacío")
    private BigDecimal thresholdBasedScoring;
}