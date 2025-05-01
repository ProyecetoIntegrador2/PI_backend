package com.udea.autoevaluacion.models;

import java.util.List;

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

    @OneToMany(mappedBy = "questionDefinition", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "questionDefinition")
    private List<AnswerOption> answerOptions;

    @ManyToOne
    @JoinColumn(name = "part_definition_id", nullable = false)
    @JsonBackReference(value = "partDefinition")
    private PartDefinition partDefinition;
}
