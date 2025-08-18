package com.example.demo.repository;

import java.time.LocalDate;
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

import com.example.demo.domain.Experiencia;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.repositories.BuscaRepository;
import com.example.demo.repositories.ExperienciaRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ExperienciaRepositoryTests {
    
    @Autowired
    private ExperienciaRepository expRepo;

    @Autowired
    private BuscaRepository buscaRepo;

    private List<Experiencia> listaExperiencias; 
    private Busca b1;
    private Busca b2;

    @BeforeEach
    public void init () {
        b1 = new Busca("Juan Pérez", "juan.perez@email.com", "Madrid", "600123456", "XyZ9@hello");
        b2 = new Busca("Juan 2", "juan.perez@email.com", "Madrid", "600123456", "Abc123!");

        listaExperiencias = new ArrayList<>();

        listaExperiencias.add(new Experiencia(null, "Desarrollador Junior A", "Empresa A", LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), b1));
        listaExperiencias.add(new Experiencia(null, "Desarrollador Backend B", "Empresa B", LocalDate.of(2021, 2, 1), LocalDate.of(2022, 6, 1), b1));
        listaExperiencias.add(new Experiencia(null, "Desarrollador Backend C", "Empresa B", LocalDate.of(2021, 2, 1), LocalDate.of(2022, 6, 1), b2));

        buscaRepo.save(b1);
        buscaRepo.save(b2);

        expRepo.saveAll(listaExperiencias);
    }

    @Test
    public void ExperienciaRepository_deleteAllExperienciaByBuscaId_RemoveAllExperienciaWithGivenId() {
        // Ejecuta el método
        expRepo.deleteAllExperienciaByBuscaId(b1.getId());

        // Obtén la lista actualizada después de ejecutar el método
        List<Experiencia> experienciasRestantes = expRepo.findAll();

        // Realiza la comprobación
        Assertions.assertThat(experienciasRestantes)
              .allMatch(exp -> !exp.getBusca().getId().equals(b1.getId()));

    }

    
}
