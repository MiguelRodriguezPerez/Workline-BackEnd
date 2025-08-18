package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.repositories.BuscaRepository;
import com.example.demo.repositories.ContrataRepository;
import com.example.demo.repositories.OfertaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class OfertaRepositoryTests {
    
    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private BuscaRepository buscaRepository;

    @Autowired
    private ContrataRepository contrataRepository;

    private List<Oferta> listaOfertas;
    private List<Busca> listaBusca;

    private Contrata contrata;

    @BeforeEach
    public void init () {
        contrata = new Contrata("Empresa A", "contacto@empresaA.com", "Madrid", "600123456", "Pass@123");
        contrata.setListaOfertas(new ArrayList<>());
        contrataRepository.save(contrata);

        listaOfertas = new ArrayList<>();
        listaOfertas.add(new Oferta("Desarrollador Java", "IT", "Backend y APIs", 
            "Madrid", 30000.0, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.PRESENCIAL, 
            contrata));
        listaOfertas.add(new Oferta("Analista de Datos", "IT", "Análisis de grandes volúmenes de datos", 
            "Barcelona", 28000.0, TipoContrato.TEMPORAL, (byte)35, ModalidadTrabajo.HIBRIDO, 
            contrata));
        listaOfertas.add(new Oferta("Diseñador UX/UI", "Diseño", "Experiencia de usuario", 
            "Valencia", 25000.0, TipoContrato.DISCONTINUO, (byte)40, ModalidadTrabajo.TELETRABAJO, 
            contrata));
        listaOfertas.add(new Oferta("Project Manager", "Gestión", "Gestión de proyectos IT", 
            "Sevilla", 35000.0, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, 
            contrata));

        ofertaRepository.saveAll(listaOfertas);


        listaBusca = new ArrayList<>();

        listaBusca.add(new Busca("Juan Pérez", "juan.perez@email.com", 
            "Madrid", "600123456", "XyZ9@hello"));
        listaBusca.add(new Busca("Ana López", "ana.lopez@email.com", 
            "Barcelona", "600654321", "Abc123!"));
        listaBusca.add(new Busca("Carlos García", "carlos.garcia@email.com", 
            "Valencia", "600987654", "Pass@123"));
        listaBusca.add(new Busca("Lucía Martínez", "lucia.martinez@email.com", 
            "Sevilla", "600111222", "Qwerty!1"));
        
        buscaRepository.saveAll(listaBusca);
    }

    @Test
    public void OfertaRepository_deleteAllCandidatosFromContrataId_checkContrataDoesNotHaveCandidatesOnHisOffers() {
        List<Busca> sublistaBusca = listaBusca.subList(0, 2);
        // SharedReferences error
        sublistaBusca.forEach(busca -> busca.setListaOfertas(
            new HashSet<>(contrata.getListaOfertas())
        ));

        contrata.getListaOfertas().forEach(oferta -> oferta.setListaCandidatos(new HashSet<>(sublistaBusca)));

        ofertaRepository.saveAll(contrata.getListaOfertas());
        buscaRepository.saveAll(sublistaBusca);

        ofertaRepository.deleteAllCandidatosFromContrataId(contrata.getId());

        Contrata contrataActualizado = contrataRepository.findById(contrata.getId()).orElseThrow();

        Assertions.assertThat(contrataActualizado.getListaOfertas())
            .allMatch(oferta -> oferta.getListaCandidatos().isEmpty());
    }

    @Test
    public void OfertaRepository_removeAllCandidatesFromOferta_checkOfertaLosesAllBusca() {
        Oferta oferta = listaOfertas.get(0);

        listaBusca.forEach(busca -> {
            busca.getListaOfertas().add(oferta);
            oferta.getListaCandidatos().add(busca);
            
            buscaRepository.save(busca);
            ofertaRepository.save(oferta);
        });

        ofertaRepository.removeAllCandidatesFromOferta(oferta.getId());

        Oferta updatedOferta = ofertaRepository.findById(oferta.getId()).orElseThrow();

        Assertions.assertThat(updatedOferta.getListaCandidatos().isEmpty());
    }

    @Test
    public void OfertaRepository_removeAllOfertasFromContrata_checkContrataDeletesAllHisOfertas() {
        ofertaRepository.deleteAllCandidatosFromContrataId(contrata.getId());
        ofertaRepository.deleteAllOfertasByContrataId(contrata.getId());

        Contrata contrataActualizado = contrataRepository.findById(contrata.getId()).orElseThrow();

        Assertions.assertThat(contrataActualizado.getListaOfertas().isEmpty());
    }


}
