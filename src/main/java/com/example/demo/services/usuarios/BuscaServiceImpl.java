package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.modelView.BuscaView;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.repositories.BuscaRepository;

@Service
public class BuscaServiceImpl implements BuscaService {

    @Autowired
    BuscaRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Busca guardar(Busca busca) {
        busca.setPassword(passwordEncoder.encode(busca.getPassword()));
        return repo.save(busca);
    }

    @Override
    public Busca guardarSinEncriptar(Busca busca) {
        return repo.save(busca);
    }

    @Override
    public Busca guardarNuevoUsuarioFromDto(NuevoUsuarioDto dto) {
        Busca busca = this.convertirNuevoUsuarioDtoABusca(dto);
        Busca resultado = this.guardar(busca);
        return resultado;
    }

    @Override
    public void borrar(Long id) {
        /*
         * Este método solo se llama cuando el propio usuario borra su cuenta, por lo
         * que
         * se forzará un logout
         */
        repo.deleteById(id);
    }

    @Override
    public void borrarCuenta() {
        // Asume que ya se desinscribio de todas las ofertas
        Busca busca = this.obtenerBuscaConectado();
        /*
         * Recuerda que conocimientos y experiencias estan configuradas para borrarse
         * si su busca desaparece
         */
        this.borrar(busca.getId());
    }

    @Override
    public List<Busca> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Busca obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Busca obtenerPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Override
    public boolean esNombreRepetido(String nombre) {
        if (this.obtenerPorNombre(nombre) != null)
            return true;
        else
            return false;
    }

    @Override
    public Busca obtenerBuscaConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken)
            return null;
        Busca busca = obtenerPorNombre(auth.getName());
        return busca;
    }

    @Override
    public Boolean estaInscritoOferta(Long id) {

        for (Oferta oferta : this.obtenerBuscaConectado().getListaOfertas()) {
            if (oferta.getId() == id)
                return true;
        }

        return false;
    }

    @Override
    public BuscaView convertirBuscaABuscaView(Busca busca) {
        return new BuscaView(busca.getNombre(), busca.getEmail(), busca.getTelefono(),
                busca.getCiudad(), busca.getListaConocimientos(), busca.getListaExperiencias());
    }

    @Override
    public Busca convertirNuevoUsuarioDtoABusca(NuevoUsuarioDto dto) {
        // Contraseña sin encriptar
        return new Busca(dto.getNombre(), dto.getEmail(),
                dto.getCiudad(), dto.getTelefono(), dto.getPassword());
    }

}
