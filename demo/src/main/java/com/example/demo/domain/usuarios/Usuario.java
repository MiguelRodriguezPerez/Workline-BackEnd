package com.example.demo.domain.usuarios;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public class Usuario {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(unique = true)
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
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{14,}$", message = "La contraseña debe tener como minímo 14, combinando mayúsculas, minúsculas y carácteres especiales")
    private String password;

    @NotNull
    private Rol rol;

    public Usuario(@NotNull String nombre,
            @NotNull @Email(message = "Por favor, proporciona un correo electrónico válido") String email, @Nullable String ciudad,
            @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener nueve dígitos") String telefono,
            @NotNull String password,Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
    }

     
}
