package com.udea.autoevaluacion.models;

import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "question_definitions")
public class QuestionDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int questionNumber;

    @Column(nullable = false)
    private String questionText;

    @OneToMany(mappedBy = "questionDefinition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JsonManagedReference(value = "questionDefinition")
    private List<AnswerOptionDefinition> answerOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_definition_id", nullable = false)
    @JsonBackReference(value = "partDefinition")
    private PartDefinition partDefinition;
}
