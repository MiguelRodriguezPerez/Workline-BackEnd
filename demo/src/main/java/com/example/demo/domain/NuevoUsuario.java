package com.example.demo.domain;

import org.springframework.lang.Nullable;

import com.example.demo.domain.usuarios.Rol;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 25, message = "El nombre debe tener entre 1 y 25 caracteres")
    private String nombre;


    @NotNull
    @Size(min = 1, max = 35, message = "El email debe tener como máximo 35 caracteres")
    @Email(message = "Por favor, proporciona un correo electrónico válido")
    private String email;

    @NotNull
    private String ciudad;
    
    @Nullable
    @Pattern(regexp = "^(6|7|8|9)\\d{8}$", message = "El número de teléfono debe comenzar con 6, 7, 8 o 9 y contener solo dígitos")
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos")
    private String telefono;

    @NotNull
    @Size(min = 1, max = 30, message = "El password debe tener como minímo 14 carácteres y como máximo 30")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).*$", message = "La contraseña debe combinar mayúsculas, minúsculas y caracteres especiales")
    private String password;

    @NotNull
    private Rol rol;
}
