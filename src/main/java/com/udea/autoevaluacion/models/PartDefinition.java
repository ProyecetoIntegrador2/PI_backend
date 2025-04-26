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
@Table(name = "part_definitions")
public class PartDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int partNumber;

    @Column(nullable = false)
    private String partName;

    @ManyToOne
    @JoinColumn(name = "form_definition_id", nullable = false)
    @JsonBackReference(value = "formDefinition")
    private FormDefinition formDefinition;

    @OneToMany(mappedBy = "partDefinition")
    @JsonManagedReference(value = "partDefinition")
    private List<QuestionDefinition> questionDefinitions;
}
