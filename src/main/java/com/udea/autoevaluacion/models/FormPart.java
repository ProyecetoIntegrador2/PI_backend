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
@Table(name = "form_parts")
public class FormPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int partNumber;

    @Column(nullable = false)
    private String partName;

    @ManyToOne
    @JoinColumn(name = "form_submission_id", nullable = false)
    private FormSubmission formSubmission;

    @OneToMany(mappedBy = "formParts")
    private List<FormQuestion> questions;

    @OneToOne(mappedBy = "submission_metrics")
    private SubmissionMetrics submissionMetrics;
}
