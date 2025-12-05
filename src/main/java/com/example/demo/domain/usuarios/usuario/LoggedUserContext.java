package com.example.demo.domain.usuarios.usuario;

import java.util.Set;

import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;
import com.example.demo.domain.usuarios.busca.experiencia.ExperienciaDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedUserContext {

    private String username;
    private String email;
    private Set<ConocimientoDto> conocimientos;
    private Set<ExperienciaDto> experiencias;
    private Rol rol;

}
