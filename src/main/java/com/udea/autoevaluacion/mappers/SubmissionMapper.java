package com.udea.autoevaluacion.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.udea.autoevaluacion.dtos.*;
import com.udea.autoevaluacion.models.*;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "formName", source = "formDefinition.formName")
    @Mapping(target = "formVersion", source = "formDefinition.formVersion")
    SubmissionDTO toSubmissionDTO(Submission submission);

    SubmissionMetricsDTO toSubmissionMetricsDTO(SubmissionMetrics submissionMetrics);

    @Mapping(target = "partNumber" , source = "partDefinition.partNumber")
    @Mapping(target = "partName" , source = "partDefinition.partName")
    SubmissionPartDTO toSubmissionPartDTO(SubmissionPart submissionPart);

    @Mapping(target = "questionNumber", source = "questionDefinition.questionNumber")
    @Mapping(target = "questionText", source = "questionDefinition.questionText")
    @Mapping(target = "actualLevel", source = "actualOption.optionLevel")
    @Mapping(target = "actualOptionText", source = "actualOption.description")
    @Mapping(target = "targetLevel", source = "targetOption.optionLevel")
    @Mapping(target = "targetOptionText", source = "targetOption.description")
    SubmissionAnswerDTO toSubmissionAnswerDTO(SubmissionAnswer submissionAnswer);

    SubmissionPartMetricsDTO toSubmissionPartMetricsDTO(SubmissionPartMetrics submissionPartMetrics);

    List<SubmissionDTO> toSubmissionDTOs(List<Submission> submissions);
}