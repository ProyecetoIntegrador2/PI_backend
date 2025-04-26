package com.udea.autoevaluacion.mappers;

import org.mapstruct.Mapper;

import com.udea.autoevaluacion.dtos.*;
import com.udea.autoevaluacion.models.*;

@Mapper(componentModel = "spring")
public interface DefinitionMapper {

    FormDefinitionDTO toFormDefinitionDTO(FormDefinition formDefinition);

    PartDefinitionDTO toPartDefinitionDTO(PartDefinition partDefinition);

    QuestionDefinitionDTO toQuestionDefinitionDTO(QuestionDefinition questionDefinition);
}
