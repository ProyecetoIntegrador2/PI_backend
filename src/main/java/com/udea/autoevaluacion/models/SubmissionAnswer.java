package com.udea.autoevaluacion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submission_questions")
public class SubmissionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_definition_id", nullable = false)
    private QuestionDefinition questionDefinition;

    @Column(nullable = false)
    private int actualLevel;

    @Column(nullable = false)
    private int targetLevel;

    @ManyToOne
    @JoinColumn(name = "submission_part_id", nullable = false)
    private SubmissionPart submissionPart;
}
