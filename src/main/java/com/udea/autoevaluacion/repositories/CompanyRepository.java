package com.udea.autoevaluacion.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    
}
