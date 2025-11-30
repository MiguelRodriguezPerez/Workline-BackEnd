package com.example.demo.services.usuarios.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.busca.BuscaDto;
import com.example.demo.domain.usuarios.contrata.Contrata;
import com.example.demo.domain.usuarios.contrata.ContrataDto;
import com.example.demo.domain.usuarios.usuario.UserContextInterface;
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
                .nombre(contrata.getNombre())
                .email(contrata.getEmail())
                .ciudad(contrata.getCiudad())
                .rol(contrata.getRol())
                .build();
    }



    public BuscaDto mapBuscaEntityToDto(Busca busca) {
        
        return BuscaDto.builder()
            .nombre(busca.getNombre())
            .email(busca.getEmail())
            .ciudad(busca.getCiudad())
            .rol(busca.getRol())
            .listaConocimientos(
                conocimientoMapper.mapConocimientoSetEntityToDto(
                    busca.getListaConocimientos()
                )
            )
            .listaExperiencias(
                experienciaMapper.mapExperienciaSetEntityToDto(
                    busca.getListaExperiencias()
                )
            )
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

    public UserContextInterface mapUsuarioEntityToUserContextInterface(Usuario usuario) {
        return UserContextInterface.builder()
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                // Sospechoso de fallar
                // TODO: Verificar e implementar enum
                .rol(usuario.getRol().toString())
                .build();
    }
}
