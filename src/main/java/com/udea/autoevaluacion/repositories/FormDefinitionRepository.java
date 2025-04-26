package com.udea.autoevaluacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.FormDefinition;
import java.util.List;


@Repository
public interface FormDefinitionRepository extends JpaRepository<FormDefinition, Long> {
    List<FormDefinition> findAllByFormName(String name);
    
    /* //Recupera la ultima version del formulario por nombre.
    Optional<FormDefinition> findTopByNameOrderByVersionDesc(String name);

    //Recupera la version del formulario por nombre y version.
    Optional<FormDefinition> findByNameAndVersion(String name, String version); */
}