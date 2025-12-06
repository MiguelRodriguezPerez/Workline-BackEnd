package com.example.demo.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.busca.BuscaDto;
import com.example.demo.domain.usuarios.busca.conocimiento.Conocimiento;
import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;
import com.example.demo.domain.usuarios.busca.experiencia.Experiencia;
import com.example.demo.domain.usuarios.busca.experiencia.ExperienciaDto;
import com.example.demo.services.usuarios.busca.BuscaService;
import com.example.demo.services.usuarios.conocimiento.ConocimientoMapper;
import com.example.demo.services.usuarios.conocimiento.ConocimientoService;
import com.example.demo.services.usuarios.experiencia.ExperienciaService;
import com.example.demo.services.usuarios.usuario.UsuarioMapper;

@RequestMapping("/busca/api")
@RestController
public class BuscaController {

    @Autowired
    BuscaService buscaService;

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    ConocimientoService conocimientoService;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    ConocimientoMapper conocimientoMapper;

    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<BuscaDto> getBuscaByIdEndpoint(@PathVariable Long id) {
        BuscaDto resultado = usuarioMapper.mapBuscaEntityToDto(
                buscaService.obtenerPorId(id));

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/misExperiencias")
    public ResponseEntity<Set<Experiencia>> getMyExperiencias() {
        Set<Experiencia> resultado = buscaService.obtenerBuscaConectado().getListaExperiencias();
        if (resultado.isEmpty() || resultado == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevaExperiencia")
    public ResponseEntity<Experiencia> submitNewExperiencia(@RequestBody ExperienciaDto dto) {
        Experiencia experiencia = experienciaService.convertirExperienciaDtoAExperiencia(dto);
        experienciaService.guardarExperienciaFromBusca(experiencia);
        return new ResponseEntity<>(experiencia, HttpStatus.CREATED);
    }

    @PutMapping("/editarExperiencia/{id}")
    public ResponseEntity<Experiencia> editExperienciaEndpoint(@RequestBody ExperienciaDto expRequest,
            @PathVariable Long id) {
        Experiencia experiencia = experienciaService.guardarCambios(expRequest, id);
        return new ResponseEntity<>(experiencia, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarExperiencia/{id}")
    public ResponseEntity<Void> deleteExperienciaEndpoint(@PathVariable Long id) {
        experienciaService.borrarExperiencia(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/misConocimientos")
    public ResponseEntity<Set<Conocimiento>> getMyConocimientos() {
        Set<Conocimiento> resultado = buscaService.obtenerBuscaConectado().getListaConocimientos();
        if (resultado.isEmpty() || resultado == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevoConocimiento")
    public ResponseEntity<Conocimiento> submitNewConocimiento(@RequestBody ConocimientoDto conocimientoDto) {
        Conocimiento conocimiento = conocimientoMapper.mapConocimientoDtoToEntity(conocimientoDto);
        Conocimiento resultado = conocimientoService.guardarConocimientoFromBusca(conocimiento);

        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @PutMapping("/editarConocimiento")
    public ResponseEntity<Conocimiento> editConocimientoEndpoint(@RequestBody ConocimientoDto conocimientoDto) {
        Conocimiento resultado = conocimientoService.guardarCambios(conocimientoDto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarConocimiento/{id}")
    public ResponseEntity<Void> deleteConocimientoEndpoint(@PathVariable Long id) {
        conocimientoService.borrarConocimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/estaInscritoOferta/{id}")
    public ResponseEntity<Boolean> getListOfertaId(@PathVariable Long id) {
        return new ResponseEntity<>(buscaService.estaInscritoOferta(id), HttpStatus.OK);
    }

    @GetMapping("/miListaOfertas")
    public ResponseEntity<Set<Oferta>> getMyListOfertas() {
        Set<Oferta> resultado = buscaService.obtenerBuscaConectado().getListaOfertas();
        if (resultado.size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
