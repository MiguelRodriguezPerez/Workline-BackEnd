package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.openApi.customClasses.PageOfertaDtoJobSearchOpenApiDoc;
import com.example.demo.domain.entidadesApi.PaginaJobSearchRequest;
import com.example.demo.domain.ofertas.OfertaDtoEmployer;
import com.example.demo.domain.ofertas.OfertaDtoJobSearch;
import com.example.demo.services.ofertas.OfertaMapper;
import com.example.demo.services.ofertas.OfertaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Oferta", description = "Endpoints to run CRUD tasks over Oferta entities")
@RequestMapping("/ofertas/api")
@RestController
public class OfertasController {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    OfertaMapper ofertaMapper;

    @Operation(operationId = "getBusquedaOfertaPage", summary = "Returns a list of Ofertas based on search and page number requested", description = "This endpoint recieve parameters to filter Ofertas and the number of the page "
            +
            "of said filtered ofertas. Both of these parameters are present in OfertaDtoJobSearch object " +
            "requested as argument")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search successfully ran", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PageOfertaDtoJobSearchOpenApiDoc.class))))
    })
    @PostMapping("/busqueda")
    public ResponseEntity<Page<OfertaDtoJobSearch>> getBusquedaOfertaPage(
            @RequestBody PaginaJobSearchRequest paginaJobSearchRequest) {
        if (paginaJobSearchRequest.getBusquedaOferta() != null) {
            Page<OfertaDtoJobSearch> resultado = ofertaService.obtenerPaginaOfertas(
                    paginaJobSearchRequest
            );
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }

    @GetMapping("/obtenerOfertaPorId/jobSearch/{id}")
    public ResponseEntity<OfertaDtoJobSearch> getOfertaByIdApiJobSearch(@PathVariable Long id) {
        OfertaDtoJobSearch resultado = ofertaMapper.mapOfertaEntityToJobSearchDto(
                ofertaService.obtenerPorId(id));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtenerOfertaPorId/employer/{id}")
    public ResponseEntity<OfertaDtoEmployer> getOfertaByIdApiEmployerDto(@PathVariable Long id) {
        OfertaDtoEmployer resultado = ofertaMapper.mapOfertaEntityToEmployerDto(
                ofertaService.obtenerPorId(id));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/inscribirBusca/{id}")
    public ResponseEntity<Void> suscribeBuscaInOferta(@PathVariable Long id) {
        ofertaService.inscribirBuscaConectadoWrapper(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/desinscribirBusca/{id}")
    public ResponseEntity<Void> unsuscribeBuscaInOferta(@PathVariable Long id) {
        ofertaService.desinscribirBuscaConectadoWrapper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
