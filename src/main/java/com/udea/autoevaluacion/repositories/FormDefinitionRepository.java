package com.udea.autoevaluacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.FormDefinition;

@Repository
public interface FormDefinitionRepository extends JpaRepository<FormDefinition, Long> {
    //Recupera la ultima version del formulario por nombre.
    Optional<FormDefinition> findTopByNameOrderByVersionDesc(String name);

    //Recupera la version del formulario por nombre y version.
    Optional<FormDefinition> findByNameAndVersion(String name, String version);
}