package com.udea.autoevaluacion.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.Submission;
import com.udea.autoevaluacion.models.User;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findAllByUser_Id(Long userId);

    // Recupera toda la submission con sus partes, respuestas y metricas.
    @Query("SELECT s FROM Submission s JOIN FETCH s.submissionParts sp JOIN FETCH sp.submissionAnswers sa JOIN FETCH sa.questionDefinition qd JOIN FETCH sp.partDefinition pd LEFT JOIN FETCH sp.submissionPartMetrics pm WHERE s.id = :id")
    Optional<Submission> findByIdWithDetails(@Param("id") Long id);
}
