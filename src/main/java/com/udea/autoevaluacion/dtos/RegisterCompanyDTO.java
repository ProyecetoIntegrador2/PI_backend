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
public class RegisterCompanyDTO {
    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;

    @NotBlank(message = "El tipo de organización no puede ser vacío")
    private String organizationType;

    @NotBlank(message = "El sector de la organización no puede ser vacío")
    private String organizationSector;

    @NotBlank(message = "El país no puede ser vacío")
    private String country;

    @NotBlank(message = "El número de empleados no puede ser vacío")
    private String numberOfEmployees;
}
