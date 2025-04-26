package com.udea.autoevaluacion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.udea.autoevaluacion.dtos.*;
import com.udea.autoevaluacion.models.*;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "formName", source = "formDefinition.name")
    @Mapping(target = "formVersion", source = "formDefinition.version")
    SubmissionDTO toSubmissionDTO(Submission submission);

    @Mapping(target = "partNumber" , source = "partDefinition.partNumber")
    @Mapping(target = "partName" , source = "partDefinition.partName")
    SubmissionPartDTO toSubmissionPartDTO(SubmissionPart submissionPart);

    @Mapping(target = "questionNumber", source = "questionDefinition.questionNumber")
    @Mapping(target = "questionText", source = "questionDefinition.questionText")
    SubmissionAnswerDTO toSubmissionAnswerDTO(SubmissionAnswer submissionAnswer);

    SubmissionPartMetricsDTO toSubmissionPartMetricsDTO(SubmissionPartMetrics submissionPartMetrics);
}