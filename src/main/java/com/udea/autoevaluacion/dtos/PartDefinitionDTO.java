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
public class PartDefinitionDTO {
    private int partNumber;
    private String partName;
    private List<QuestionDefinitionDTO> questionDefinitions;
}
