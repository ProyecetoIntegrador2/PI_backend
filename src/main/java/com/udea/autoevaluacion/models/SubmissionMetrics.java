package com.udea.autoevaluacion.models;

import java.math.BigDecimal;

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
@Table(name = "submission_metrics")
public class SubmissionMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @Column(name = "average_actual_score")
    private int averageActualScore;

    @Column(name = "average_desired_score")
    private int averageDesiredScore;

    // Punto de corte mayoritario
    @Column(name = "majority_cut_off_level")
    private int majorityCutOffLevel;

    // Scoring basado en umbral
    @Column(name = "threshold_based_scoring")
    private int thresholdBasedScoring;
}
