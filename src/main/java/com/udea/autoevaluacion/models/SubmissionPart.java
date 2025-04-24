package com.udea.autoevaluacion.models;

import java.util.List;

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
@Table(name = "submission_parts")
public class SubmissionPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int partNumber;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @OneToMany(mappedBy = "submissionPart")
    private List<SubmissionAnswer> submissionAnswers;

    @OneToOne(mappedBy = "submissionPart")
    private SubmissionMetrics submissionMetrics;
}
