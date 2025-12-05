package com.example.demo.services.usuarios.usuario;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.busca.BuscaDto;
import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;
import com.example.demo.domain.usuarios.busca.experiencia.ExperienciaDto;
import com.example.demo.domain.usuarios.contrata.Contrata;
import com.example.demo.domain.usuarios.contrata.ContrataDto;
import com.example.demo.domain.usuarios.usuario.LoggedUserContext;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.example.demo.domain.usuarios.usuario.UsuarioSettignsDto;
import com.example.demo.services.usuarios.busca.BuscaService;
import com.example.demo.services.usuarios.conocimiento.ConocimientoMapper;
import com.example.demo.services.usuarios.experiencia.ExperienciaMapper;

@Component
public class UsuarioMapper {

    @Autowired
    ConocimientoMapper conocimientoMapper;

    @Autowired
    ExperienciaMapper experienciaMapper;

    @Autowired
    BuscaService buscaService;

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

    public UsuarioSettignsDto mapUsuarioEntityToUsuarioSettignsDto(Usuario usuario) {
        return UsuarioSettignsDto.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .ciudad(usuario.getCiudad())
                .build();
    }

    public LoggedUserContext mapUsuarioEntityToUserContextInterface(Usuario usuario) {
        Set<ConocimientoDto> conocimientos = new HashSet<>();
        Set<ExperienciaDto> experiencias = new HashSet<>();
        Busca busca = buscaService.obtenerPorNombre(usuario.getNombre());

        if (busca != null) {
                conocimientos = conocimientoMapper.mapConocimientoSetEntityToDto(
                        busca.getListaConocimientos()
                );

                experiencias = experienciaMapper.mapExperienciaSetEntityToDto(
                        busca.getListaExperiencias()
                );
        }

        return LoggedUserContext.builder()
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .conocimientos(conocimientos)
                .experiencias(experiencias)
                .build();
    }
}
