package com.udea.autoevaluacion.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "El email no puede ser vacío")
    private String email;

    @NotBlank(message = "La contraseña no puede ser vacía")
    private String password;
}
