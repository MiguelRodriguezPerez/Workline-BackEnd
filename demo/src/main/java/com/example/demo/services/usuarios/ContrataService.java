package com.example.demo.services.usuarios;

import java.util.List;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Contrata;

public interface ContrataService {
    
    Contrata guardarContrata(Contrata contrata);
    Contrata guardarContrataSinEncriptar(Contrata contrata);
    Contrata guardarContrataDesdeNuevoUsuario(NuevoUsuario nuevoUsuario);
    void borrarContrata(Long id);
    List<Contrata> obtenerTodos();
    Contrata obtenerPorId(Long id);
    Contrata obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);
    String obtenerNombre();
    Contrata obtenerContrataConectado();
    List<Oferta> obtenerPaginaOfertasPublicadas(Integer paginaElecta);
    int siguientePaginaOfertasPublicadas(Integer pagina);
    int anteriorPaginaOfertasPublicadas(Integer pagina);
    int obtenerNumeroPaginasOfertasPublicadas();
    
}
