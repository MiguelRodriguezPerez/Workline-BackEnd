package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.domain.usuarios.buscaData.Busca;
import com.example.demo.repositories.BuscaRepository;

@Service
public class BuscaServiceImpl implements BuscaService{

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
    public void borrar(Long id) {
        repo.deleteById(id);
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
    public boolean esNombreRepetido(String nombre){
        if(this.obtenerPorNombre(nombre) != null) return true;
        else return false;
    }

    @Override
    public String obtenerNombre() {
       return obtenerBuscaConectado().getNombre();
    }

    @Override
    public Busca obtenerBuscaConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Busca busca = obtenerPorNombre(auth.getName());
        return busca;
    }

    @Override
    public boolean estaSuscritoOferta(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'estaSuscritoOferta'");
    }

    @Override
    public Busca guardarBuscaDesdeNuevoUsuario(NuevoUsuario nuevoUsuario) {
        Busca busca = new Busca(nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getCiudad(), nuevoUsuario.getTelefono(), nuevoUsuario.getPassword());
        Busca busca2 = this.guardar(busca);

        System.out.println(busca2 + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        
        return busca2;
    }
    

}
