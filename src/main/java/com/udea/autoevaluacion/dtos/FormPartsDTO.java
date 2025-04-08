package com.udea.autoevaluacion.dtos;

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
public class FormPartsDTO {
    @NotBlank(message = "El número de parte del formulario no puede ser vacío")
    private int partNumber;

    @NotBlank(message = "El nombre de parte del formulario no puede ser vacío")
    private String partName;

    @NotBlank(message = "Las preguntas de la parte no pueden estar vacias")
    private List<FormQuestionsDTO> questions;
}
