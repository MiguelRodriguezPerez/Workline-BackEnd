package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.modelView.BuscaView;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoEmployer;
import com.example.demo.domain.ofertas.OfertaDtoJobSearch;
import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.services.ofertas.OfertaMapper;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.busca.BuscaService;
import com.example.demo.services.usuarios.contrata.ContrataService;

@RequestMapping("/contrata/api")
@RestController
public class ContrataController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;

    @Autowired
    OfertaMapper ofertaMapper;

    /*
     * Lo necesitas para obtener busca por nombre cuando el contrata ve el candidato
     */
    @Autowired
    BuscaService buscaService;

    @GetMapping("/ofertas/pagina/{num}") // Nota: Borraste @CookieValue(defaultValue = "no-cookie-found") String
                                         // jwtToken,
    public ResponseEntity<Page<OfertaDtoEmployer>> getPaginaApi(@PathVariable int num) {
        Page<OfertaDtoEmployer> resultado = contrataService.obtenerPaginaOfertasPublicadas(Integer.valueOf(num));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevaOferta")
    public ResponseEntity<OfertaDtoEmployer> registrarOferta(@RequestBody OfertaDtoEmployer ofertaDtoApi) {
        OfertaDtoEmployer resultado = ofertaService.guardarNuevaOferta(ofertaDtoApi);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @PutMapping("/editarOferta")
    public ResponseEntity<OfertaDtoEmployer> actualizarOferta(@RequestBody OfertaDtoEmployer ofertaDtoApi) {
        OfertaDtoEmployer resultado = ofertaService.actualizarOferta(ofertaDtoApi);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarOferta/{id}")
    public ResponseEntity<Void> borrarOferta(@PathVariable Long id) {
        ofertaService.borrarOfertaWrapper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/obtenerListaCandidatos/{id}")
    public ResponseEntity<List<Busca>> getListBusca(@PathVariable Long id) {
        List<Busca> resultado = ofertaService.obtenerPorId(id).getListaCandidatos();
        if (resultado.size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtenerCandidato/{nombre}")
    private ResponseEntity<BuscaView> getBuscaByNombre(@PathVariable String nombre) {
        Busca busca = buscaService.obtenerPorNombre(nombre);
        if (busca == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        BuscaView resultado = buscaService.convertirBuscaABuscaView(busca);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtenerBuscaPorNombre/{nombre}")
    public ResponseEntity<Busca> getBuscaByName(@PathVariable String nombre) {
        Busca resultado = buscaService.obtenerPorNombre(nombre);
        if (resultado == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
