package com.udea.autoevaluacion.models;

import java.util.List;

import org.hibernate.annotations.BatchSize;
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
@Table(name = "form_definitions")
public class FormDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String formName;

    @Column(nullable = false)
    private String formVersion;

    @OneToMany(mappedBy = "formDefinition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 15)
    @JsonManagedReference(value = "formDefinition")
    private List<PartDefinition> partDefinitions;
}
