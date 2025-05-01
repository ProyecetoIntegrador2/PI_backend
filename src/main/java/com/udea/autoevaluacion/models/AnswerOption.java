package com.udea.autoevaluacion.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "answer_option_definitions")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_definition_id", nullable = false)
    @JsonBackReference(value = "questionDefinition")
    private QuestionDefinition questionDefinition;

    @Column(nullable = false)
    private int optionLevel;

    @Column(nullable = false)
    private String description;
}