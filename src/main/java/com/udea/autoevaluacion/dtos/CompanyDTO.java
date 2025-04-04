package com.udea.autoevaluacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private String name;
    private String organizationType;
    private String organizationSector;
    private String country;
    private String numberOfEmployees;
}
