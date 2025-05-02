package com.udea.autoevaluacion.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompanyDTO {
    private Long id;
    private String name;
    private String organizationType;
    private String organizationSector;
    private String country;
    private String numberOfEmployees;
}
