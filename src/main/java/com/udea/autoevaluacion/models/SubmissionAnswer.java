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
@Table(name = "form_questions")
public class SubmissionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int questionNumber;

    @Column(nullable = false)
    private String questionText;   

    @ManyToOne
    @JoinColumn(name = "submission_part_id", nullable = false)
    private SubmissionPart submissionParts;
}
