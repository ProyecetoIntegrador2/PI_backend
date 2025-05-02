package com.udea.autoevaluacion.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionPartDTO {
    @Positive(message = "El número de parte debe ser positivo")
    private int partNumber;

    @NotNull(message = "Las preguntas del formulario no pueden ser nulas")
    @NotEmpty(message = "Las preguntas del formulario no pueden estar vacías")
    @Valid
    private List<RegisterSubmissionAnswerDTO> registerSubmissionAnswers;
}