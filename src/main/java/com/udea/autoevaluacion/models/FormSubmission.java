package com.udea.autoevaluacion.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;

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
public class FormSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Column(columnDefinition = "jsonb")
    private JsonNode formData;
}
