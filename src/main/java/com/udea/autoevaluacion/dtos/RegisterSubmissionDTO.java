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
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionDTO {
    @NotBlank(message = "El ID de usuario no puede ser vacío")
    private String userId;

    @NotBlank(message = "Los datos del formulario no pueden ser vacíos")
    private List<SubmissionPartDTO> submissionParts;
}