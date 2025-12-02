package com.example.demo.domain.usuarios.busca;

import java.util.Set;

import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;
import com.example.demo.domain.usuarios.busca.experiencia.ExperienciaDto;
import com.example.demo.domain.usuarios.usuario.Rol;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Builder
public class BuscaDto {

    private Long id;
    private String nombre;
    private String email;
    private String ciudad;
    private String telefono;
    private Rol rol;

    private Set<ConocimientoDto> listaConocimientos;
    private Set<ExperienciaDto> listaExperiencias;
}
