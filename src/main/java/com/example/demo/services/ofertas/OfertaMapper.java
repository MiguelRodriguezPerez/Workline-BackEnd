package com.example.demo.services.ofertas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoEmployer;
import com.example.demo.domain.ofertas.OfertaDtoJobSearch;
import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.services.usuarios.usuario.UsuarioMapper;

@Component
public class OfertaMapper {

    @Autowired
    UsuarioMapper usuarioMapper;


    public Oferta mapNewOfertaEmployerDtoToEntity (OfertaDtoEmployer ofertaDtoEmployer) {
        return Oferta.builder()
                // El id se sobreentiende nulo
                .puesto(ofertaDtoEmployer.getPuesto())
                .sector(ofertaDtoEmployer.getSector())
                .descripcion(ofertaDtoEmployer.getDescripcion())
                .ciudad(ofertaDtoEmployer.getCiudad())
                .salarioAnual(ofertaDtoEmployer.getSalarioAnual())
                .tipoContrato(ofertaDtoEmployer.getTipoContrato())
                .horas(ofertaDtoEmployer.getHoras())
                .modalidadTrabajo(ofertaDtoEmployer.getModalidadTrabajo())
                .fechaPublicacion(LocalDate.now())
                .listaCandidatos(new ArrayList<>())
                .build();
    }

    public OfertaDtoJobSearch mapOfertaEntityToJobSearchDto(Oferta oferta) {
        return OfertaDtoJobSearch.builder()
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
                        usuarioMapper.mapUsuarioEntityToContrataDto(oferta.getContrata()))
                .build();
    }

    public OfertaDtoEmployer mapOfertaEntityToEmployerDto(Oferta oferta) {
         return OfertaDtoEmployer.builder()
                .id(oferta.getId())
                .puesto(oferta.getPuesto())
                .sector(oferta.getSector())
                .descripcion(oferta.getDescripcion())
                .ciudad(oferta.getCiudad())
                .salarioAnual(oferta.getSalarioAnual())
                .tipoContrato(oferta.getTipoContrato())
                .horas(oferta.getHoras())
                .modalidadTrabajo(oferta.getModalidadTrabajo())
                .fechaPublicacion(oferta.getFechaPublicacion())
                .listaCandidatos(
                        oferta.getListaCandidatos() != null ?
                        oferta.getListaCandidatos().stream()
                                .map(usuarioMapper::mapBuscaEntityToDto) 
                                .collect(Collectors.toList())
                        : null
                )
                .build();
    }
    

    /*
     * NOTA: Las relaciones con otras entidades (Contrata) se mapean en el servicio
     */
    public Oferta mapNewOfertaDtoToEntity(OfertaDtoJobSearch ofertaDto) {
        return Oferta.builder()
                .id(null)
                .puesto(ofertaDto.getPuesto())
                .sector(ofertaDto.getSector())
                .descripcion(ofertaDto.getDescripcion())
                .ciudad(ofertaDto.getCiudad())
                .salarioAnual(ofertaDto.getSalarioAnual())
                .horas(ofertaDto.getHoras())
                .fechaPublicacion(LocalDate.now())
                .tipoContrato(ofertaDto.getTipoContrato())
                .modalidadTrabajo(ofertaDto.getModalidadTrabajo())
                .listaCandidatos(new ArrayList<>())
                .build();
    }
}
