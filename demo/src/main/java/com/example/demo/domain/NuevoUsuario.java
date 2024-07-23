package com.example.demo.domain;

import org.springframework.lang.Nullable;

import com.example.demo.domain.usuarios.Rol;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NuevoUsuario {

    @NotNull
    private String nombre;
    @NotNull
    @Email(message = "Por favor, proporciona un correo electrónico válido")
    private String email;

    @NotNull
    private String ciudad;
    
    @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener nueve dígitos")
    @Nullable
    private String telefono;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{14,}$", message = "La contraseña debe tener como minímo 14, combinando mayúsculas, minúsculas y carácteres especiales")
    private String password;

    @NotNull
    private Rol rol;
}
