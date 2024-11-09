package com.example.demo.domain.usuarios;

import java.util.Collection;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
// @Entity
@DiscriminatorColumn(name = "dtype")
@MappedSuperclass
public abstract class Usuario implements UserDetails{
    //Sospechoso de fallar (la clase entera)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Size(min = 1, max = 25, message = "El nombre debe tener entre 1 y 25 caracteres")
    @Column(unique = true)
    private String nombre;

    @NotNull
    @Email(message = "Por favor, proporciona un correo electrónico válido")
    @Size(min = 1, max = 35, message = "El email debe tener como máximo 35 caracteres")
    private String email;

    @NotNull
    private String ciudad;
    
    @Nullable
    @Pattern(regexp = "^(6|7|8|9)\\d{8}$", message = "El número de teléfono debe comenzar con 6, 7, 8 o 9 y contener solo dígitos")
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos")
    private String telefono;

    @NotNull
    // @Size(min = 14, max = 30, message = "El password debe tener como minímo 14 carácteres y como máximo 30") validación fallida
    //Tiene problemas para validar la longitud, incluso si la integras en la regex
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).*$", message = "La contraseña debe combinar mayúsculas, minúsculas y caracteres especiales")
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


    public Usuario setNombreJwt(String nombre) { 
        this.nombre = nombre; 
        return this; 
    } 

    public Usuario setEmail(String email) { 
        this.email = email; 
        return this; 
    } 


    public Usuario setPassword(String password) { 
        this.password = password; 
        return this; 
    } 

    @Override
    public String getUsername(){
        return nombre;
    }

    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return List.of(); 
    } 
    @Override 
    public boolean isAccountNonExpired() { 
        return true; 
    } 
    @Override public boolean isAccountNonLocked() { 
        return true; 
    } 
    
    @Override public boolean isCredentialsNonExpired() { 
        return true; 
    } 
    @Override public boolean isEnabled() { 
        return true; 
    }

     
}
