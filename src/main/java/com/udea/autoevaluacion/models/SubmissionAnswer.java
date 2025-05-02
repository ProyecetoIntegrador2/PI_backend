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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_definition_id", nullable = false)
    private QuestionDefinition questionDefinition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actual_option_id", nullable = false)
    private AnswerOptionDefinition actualOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_option_id", nullable = false)
    private AnswerOptionDefinition targetOption;

    @ManyToOne
    @JoinColumn(name = "submission_part_id", nullable = false)
    private SubmissionPart submissionPart;
}
