package com.udea.autoevaluacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.SubmissionAnswer;
import com.udea.autoevaluacion.models.SubmissionPart;

@Repository
public interface SubmissionAnswerRepository extends JpaRepository<SubmissionAnswer, Long> {
    // Recupera todas las SubmissionAnswer por el id de la SubmissionPart.
    List<SubmissionAnswer> findBySubmissionPart(SubmissionPart submissionPart);   
}
