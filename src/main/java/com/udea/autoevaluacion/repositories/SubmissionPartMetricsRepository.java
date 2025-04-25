package com.udea.autoevaluacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.SubmissionPartMetrics;
import com.udea.autoevaluacion.models.SubmissionPart;

@Repository
public interface SubmissionPartMetricsRepository extends JpaRepository<SubmissionPartMetrics, Long> {
    // Recupera la SubmissionPartMetrics por el id de la SubmissionPart.
    Optional<SubmissionPartMetrics> findBySubmissionPart(SubmissionPart submissionPart);
    
}
