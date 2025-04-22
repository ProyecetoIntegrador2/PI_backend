package com.udea.autoevaluacion.models;

import java.time.LocalDateTime;
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
@Table(name = "form_submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @OneToMany(mappedBy = "submission")
    private List<SubmissionPart> submissionParts;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
