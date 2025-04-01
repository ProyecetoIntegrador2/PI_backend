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
    @NotBlank(message = "La contraseña no puede ser vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "El email no puede ser vacío")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "El nombre de la empresa no puede ser vacío")
    private String companyName;

    @NotBlank(message = "La dirección no puede ser vacía")
    private String address;
}
