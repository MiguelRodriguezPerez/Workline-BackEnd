package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.repositories.ContrataRepository;
import com.example.demo.services.usuarios.ContrataServiceImpl;
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContrataServiceTest {

    @InjectMocks
    ContrataServiceImpl contrataService;

    /*En cada test tienes que definir como se van a comportar los componentes
    anotados con @Mock, menos authentication y securityContext, que como siempre se
    van a comportar igual se definen en el @BeforeEach */
    @Mock
    ContrataRepository repo;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        authentication = mock(Authentication.class);
        securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
    }


    @Test
    public void guardarCambiosNombre(){
        Contrata contrataConectado = new Contrata("Mar", 
        "maria@example.com", "Madrid", "612345678", "securePassword123");

        when(repo.save(any(Contrata.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(contrataService.obtenerContrataConectado()).thenReturn(contrataConectado);

        Contrata contrataEjemplo2 = contrataConectado;
        contrataEjemplo2.setNombre("Mar2");

        Contrata resultado = contrataService.guardarCambios(contrataEjemplo2);

        assert(resultado.getNombre()).equals("Mar2");
        assert(resultado.getEmail()).equals(contrataConectado.getEmail());
    }

    @Test
    public void guardarCambiosPassword(){
        Contrata contrataEjemplo = new Contrata("Mar", 
        "maria@example.com", "Madrid", "612345678", "securePassword123");

        when(repo.save(any(Contrata.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(passwordEncoder.encode(anyString())).thenAnswer(invocation -> invocation.getArgument(0));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(contrataService.obtenerContrataConectado()).thenReturn(contrataEjemplo);

        Contrata contrataEjemplo2 = contrataEjemplo;
        contrataEjemplo2.setPassword("otroPassword");

        Contrata resultado = contrataService.guardarCambios(contrataEjemplo2);

        assert(resultado.getPassword()).equals("otroPassword");
        assert(resultado.getCiudad()).equals(contrataEjemplo.getCiudad());
        verify(repo, times(1)).save(contrataEjemplo2);
    }
}