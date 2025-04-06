package com.udea.autoevaluacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
