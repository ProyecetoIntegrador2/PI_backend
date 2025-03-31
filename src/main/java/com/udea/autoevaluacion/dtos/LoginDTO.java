package com.udea.autoevaluacion.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotNull(message = "El usuario no puede ser null")
    @NotEmpty(message = "El usuario no puede ser vacío")
    @NotBlank(message = "El usuario no puede ser vacío")
    private String username;

    @NotNull(message = "La contraseña no puede ser null")
    @NotEmpty(message = "La contraseña no puede ser vacía")
    @NotBlank(message = "La contraseña no puede ser vacía")
    private String password;
}
