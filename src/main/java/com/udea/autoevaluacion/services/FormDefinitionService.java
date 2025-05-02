package com.udea.autoevaluacion.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.apache.maven.artifact.versioning.ComparableVersion;

import com.udea.autoevaluacion.dtos.FormDefinitionDTO;
import com.udea.autoevaluacion.mappers.DefinitionMapper;
import com.udea.autoevaluacion.models.FormDefinition;
import com.udea.autoevaluacion.repositories.FormDefinitionRepository;

@Service
public class FormDefinitionService {
    private final FormDefinitionRepository formDefinitionRepository;
    private final DefinitionMapper formDefinitionMapper;

    public FormDefinitionService(FormDefinitionRepository formDefinitionRepository, DefinitionMapper formDefinitionMapper) {
        this.formDefinitionRepository = formDefinitionRepository;
        this.formDefinitionMapper = formDefinitionMapper;
    }

    public FormDefinitionDTO getLatestFormDefinition(String formName) {
        List<FormDefinition> formDefinitions = formDefinitionRepository.findAllByFormName(formName);

        if (formDefinitions.isEmpty()) {
            throw new RuntimeException("No se encontraron formularios con el nombre: " + formName);
        }

        Optional<FormDefinition> latestFormDefinition = formDefinitions.stream()
                .max(Comparator.comparing(definition -> new ComparableVersion(definition.getFormVersion())));

        FormDefinitionDTO formDefinitionDTO = formDefinitionMapper.toFormDefinitionDTO(latestFormDefinition.get());
        
        return formDefinitionDTO;
    }
}
