package com.example.demo.controllers;

import java.util.List;
import java.util.Set;

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

import com.example.demo.domain.dtos.usuarios.oferta.OfertaDto;
import com.example.demo.domain.modelView.BuscaView;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/contrata/api")
@RestController
public class ContrataController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;

    /*
     * Lo necesitas para obtener busca por nombre cuando el contrata ve el candidato
     */
    @Autowired
    BuscaService buscaService;

    @GetMapping("/ofertas/pagina/{num}") // Nota: Borraste @CookieValue(defaultValue = "no-cookie-found") String
                                         // jwtToken,
    public ResponseEntity<Page<Oferta>> getPaginaApi(@PathVariable int num) {
        Page<Oferta> resultado = contrataService.obtenerPaginaOfertasPublicadas(Integer.valueOf(num));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevaOferta")
    public ResponseEntity<Oferta> registrarOferta(@RequestBody OfertaDto ofertaDtoApi) {
        ofertaService.guardarOfertaFromContrata(ofertaDtoApi);
        return new ResponseEntity<>(oferta, HttpStatus.CREATED);
    }

    @PutMapping("/editarOferta/{id}")
    public ResponseEntity<Oferta> actualizarOferta(@PathVariable Long id,
            @RequestBody OfertaDto ofertaDtoApi) {
        Oferta o1 = ofertaService.convertirOfertaDtoAOferta(ofertaDtoApi);
        o1.setId(id);
        Oferta ofertaDef = ofertaService.guardarCambios(o1);
        return new ResponseEntity<>(ofertaDef, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarOferta/{id}")
    public ResponseEntity<Void> borrarOferta(@PathVariable Long id) {
        ofertaService.borrarOfertaWrapper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Tuviste que diseñar un nuevo endpoint para obtener la lista de candidatos
     * porque
     * al obtenerlos por id de la manera habitual en el método toString excluías la
     * lista
     * de candidatos por problemas de recursión
     */
    @GetMapping("/obtenerNumeroCandidatos/{id}")
    public ResponseEntity<Integer> getNumberBusca(@PathVariable Long id) {
        Integer resultado = ofertaService.obtenerPorId(id).getListaCandidatos().size();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtenerListaCandidatos/{id}")
    public ResponseEntity<Set<Busca>> getListBusca(@PathVariable Long id) {
        Set<Busca> resultado = ofertaService.obtenerPorId(id).getListaCandidatos();
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
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Busca resultado = buscaService.obtenerPorNombre(nombre);
        System.out.println(resultado + "AAAAAAAAAAAAAAAAAAAAAA");
        if (resultado == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
