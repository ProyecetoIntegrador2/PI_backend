package com.udea.autoevaluacion.dtos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormSubmissionDTO {
    @NotBlank(message = "El ID de usuario no puede ser vacío")
    private String userId;

    @NotBlank(message = "La fecha de envío no puede ser vacía")
    private LocalDateTime submissionDate;

    @NotBlank(message = "Los datos del formulario no pueden ser vacíos")
    private List<FormPartsDTO> formParts;
}
