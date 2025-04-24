package com.udea.autoevaluacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.SubmissionMetrics;
import com.udea.autoevaluacion.models.SubmissionPart;

@Repository
public interface SubmissionMetricsRepository extends JpaRepository<SubmissionMetrics, Long> {
    // Recupera la SubmissionMetrics por el id de la SubmissionPart.
    Optional<SubmissionMetrics> findBySubmissionPart(SubmissionPart submissionPart);
    
}
