package com.udea.autoevaluacion.dtos;

import lombok.Value;

@Value
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String companyName;
    private String yearsOfExperienceTechnology;
}
