package com.udea.autoevaluacion.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "El nombre no puede ser vacío")
    private String firstName;

    @NotBlank(message = "El apellido no puede ser vacío")
    private String lastName;

    @NotBlank(message = "El email no puede ser vacío")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "La contraseña no puede ser vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "El cargo no puede ser vacío")
    private String jobTitle;

    @NotBlank(message = "Los años de experiencia no pueden ser vacíos")
    private String yearsOfExperienceTechnology;

    @NotBlank(message = "El ID de la empresa no puede ser vacío")
    private Long companyId;
}
