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
@Table(name = "submission_part_metrics")
public class SubmissionPartMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "submission_part_id", nullable = false)
    private SubmissionPart submissionPart;

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
