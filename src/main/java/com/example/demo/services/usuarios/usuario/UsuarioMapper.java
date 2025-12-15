package com.example.demo.services.usuarios.usuario;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.busca.BuscaDto;
import com.example.demo.domain.usuarios.contrata.Contrata;
import com.example.demo.domain.usuarios.contrata.ContrataDto;
import com.example.demo.domain.usuarios.usuario.LoggedUserContext;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.example.demo.domain.usuarios.usuario.UsuarioSettignsDto;
import com.example.demo.services.usuarios.conocimiento.ConocimientoMapper;
import com.example.demo.services.usuarios.experiencia.ExperienciaMapper;

@Component
public class UsuarioMapper {

    @Autowired
    ConocimientoMapper conocimientoMapper;

    @Autowired
    ExperienciaMapper experienciaMapper;


    public ContrataDto mapUsuarioEntityToContrataDto(Contrata contrata) {
        return ContrataDto.builder()
                .id(contrata.getId())
                .nombre(contrata.getNombre())
                .email(contrata.getEmail())
                .ciudad(contrata.getCiudad())
                .rol(contrata.getRol())
                .build();
    }

    public BuscaDto mapBuscaEntityToDto(Busca busca) {
        return BuscaDto.builder()
                .id(busca.getId())
                .nombre(busca.getNombre())
                .email(busca.getEmail())
                .ciudad(busca.getCiudad())
                .telefono(busca.getTelefono())
                .rol(busca.getRol())
                .listaConocimientos(
                        conocimientoMapper.mapConocimientoSetEntityToDto(
                                busca.getListaConocimientos()))
                .listaExperiencias(
                        experienciaMapper.mapExperienciaSetEntityToDto(
                                busca.getListaExperiencias()))
                .build();
    }

    public LoggedUserContext mapUsuarioEntityToUserContextInterface(Usuario usuario) {
        if (usuario instanceof Busca busca) 
                return mapUsuarioEntityToUserContextInterface(busca);
        
        if (usuario instanceof Contrata contrata) 
                return mapUsuarioEntityToUserContextInterface(contrata);

        throw new IllegalArgumentException();

        }

        public LoggedUserContext mapUsuarioEntityToUserContextInterface(Busca busca) {
        return LoggedUserContext.builder()
                .username(busca.getUsername())
                .email(busca.getEmail())
                .rol(busca.getRol())
                .conocimientos(
                        busca != null ? 
                        conocimientoMapper.mapConocimientoSetEntityToDto(busca.getListaConocimientos())
                        : 
                        new HashSet<>()
                )
                .experiencias(
                        busca != null ? 
                        experienciaMapper.mapExperienciaSetEntityToDto(busca.getListaExperiencias())
                        : 
                        new HashSet<>()
                )
                .build();
        }

        public LoggedUserContext mapUsuarioEntityToUserContextInterface(Contrata contrata) {
                return LoggedUserContext.builder()
                        .username(contrata.getUsername())
                        .email(contrata.getEmail())
                        .rol(contrata.getRol())
                        .conocimientos(null)
                        .experiencias(null)
                        .build();
        }

    public UsuarioSettignsDto mapUsuarioEntityToUsuarioSettignsDto(Usuario usuario) {
        return UsuarioSettignsDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .ciudad(usuario.getCiudad())
                .build();
    }

   


}
