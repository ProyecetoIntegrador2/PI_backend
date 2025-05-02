package com.udea.autoevaluacion.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubmissionDTO {
    @NotNull(message = "El ID de usuario no puede ser nulo")
    private Long userId;

    @NotNull(message = "El ID de la definicion del formulario no puede ser nulo")
    private Long formDefinitionId;

    @NotNull(message = "Las partes del formulario no pueden ser nulas")
    @NotEmpty(message = "Las partes del formulario no pueden estar vacías")
    @Valid
    private List<RegisterSubmissionPartDTO> registerSubmissionParts;
}