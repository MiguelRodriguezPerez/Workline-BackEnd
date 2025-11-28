package com.example.demo.services.ofertas;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDto;
import com.example.demo.services.usuarios.usuario.UsuarioMapper;

@Component
public class OfertaMapper {

    @Autowired
    UsuarioMapper usuarioMapper;
    
    public OfertaDto mapOfertaEntityToDto (Oferta oferta) {
        return OfertaDto.builder()
            .id(oferta.getId())
            .puesto(oferta.getPuesto())
            .sector(oferta.getSector())
            .descripcion(oferta.getDescripcion())
            .ciudad(oferta.getCiudad())
            .salarioAnual(oferta.getSalarioAnual())
            .horas(oferta.getHoras())
            .fechaPublicacion(oferta.getFechaPublicacion())
            .tipoContrato(oferta.getTipoContrato())
            .modalidadTrabajo(oferta.getModalidadTrabajo())
            .numeroCandidatos(oferta.getListaCandidatos().size())
            .contrata(
                usuarioMapper.mapUsuarioEntityToUsuarioDto(oferta.getContrata())
            )
            .build();
    }

    /* NOTA: Las relaciones con otras entidades (Contrata) se mapean en el servicio */
    public Oferta mapNewOfertaDtoToEntity (OfertaDto ofertaDto) {
        return Oferta.builder()
            .id(ofertaDto.getId())
            .puesto(ofertaDto.getPuesto())
            .sector(ofertaDto.getSector())
            .descripcion(ofertaDto.getDescripcion())
            .ciudad(ofertaDto.getCiudad())
            .salarioAnual(ofertaDto.getSalarioAnual())
            .horas(ofertaDto.getHoras())
            .fechaPublicacion(LocalDate.now())
            .tipoContrato(ofertaDto.getTipoContrato())
            .modalidadTrabajo(ofertaDto.getModalidadTrabajo())
            .build();
    }
}
