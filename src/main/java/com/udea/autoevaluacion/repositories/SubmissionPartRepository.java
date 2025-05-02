package com.udea.autoevaluacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.autoevaluacion.models.Submission;
import com.udea.autoevaluacion.models.SubmissionPart;

@Repository
public interface SubmissionPartRepository extends JpaRepository<SubmissionPart, Long> {
    List<SubmissionPart> findBySubmission(Submission submission);
}
