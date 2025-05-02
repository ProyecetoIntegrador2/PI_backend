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

    @ManyToOne
    @JoinColumn(name = "part_definition_id", nullable = false)
    private PartDefinition partDefinition;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @OneToMany(mappedBy = "submissionPart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmissionAnswer> submissionAnswers;

    @OneToOne(mappedBy = "submissionPart", cascade = CascadeType.ALL, orphanRemoval = true)
    private SubmissionPartMetrics submissionPartMetrics;
}
