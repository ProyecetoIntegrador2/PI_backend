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
public class RegisterSubmissionDTO {
    @NotBlank(message = "El ID de usuario no puede ser vacío")
    private Long userId;

    @NotBlank(message = "El ID de formulario no puede ser vacío")
    private Long formDefinitionId;

    @NotBlank(message = "Los datos del formulario no pueden ser vacíos")
    private List<RegisterSubmissionPartDTO> registerSubmissionParts;
}