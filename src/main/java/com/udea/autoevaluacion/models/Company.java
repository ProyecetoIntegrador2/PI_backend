package com.udea.autoevaluacion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String organizationType;

    @Column(nullable = false)
    private String organizationSector;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String numberOfEmployees;

    @OneToMany(mappedBy = "company")
    private List<User> users;
}
