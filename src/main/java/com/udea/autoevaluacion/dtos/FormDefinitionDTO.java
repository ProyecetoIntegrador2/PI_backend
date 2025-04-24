package com.udea.autoevaluacion.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormDefinitionDTO {
    private String name;
    private String version;
    private List<PartDefinitionDTO> partDefinitions;
}
