package com.example.demo.services.usuarios;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.modelView.BuscaView;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
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

    /*Este método sirve para actualizar los datos del usuario. Su contraseña
    se encriptará solo si la cambia*/
    @Override
    public Busca guardarCambios(Busca busca) {
        Busca buscaAntiguo = this.obtenerBuscaConectado();

        busca.setListaConocimientos(buscaAntiguo.getListaConocimientos());
        busca.setListaExperiencias(buscaAntiguo.getListaExperiencias());

        /*Este método sirve para cambiar los datos del usuario, pero también la contraseña
        Como estas dos acciones se realizan por rutas distintas, se comprueba si el contrata nuevo
        tiene una contraseña fijada. En caso positivo, significa que se accedio a la ruta para
        cambiar la contraseña, por lo que se encripta. En caso negativo, significa que esta cambiando
        el resto de datos (nombre,email...) por lo que se le asigna la contraseña antigua*/
        if(busca.getPassword() != null) busca.setPassword(passwordEncoder.encode(busca.getPassword()));
        else busca.setPassword(buscaAntiguo.getPassword());

        this.guardarSinEncriptar(busca);

        //Estas líneas sirven para cambiar el login actual

        Collection<SimpleGrantedAuthority> nowAuthorities = 
        (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext()
                                                                .getAuthentication()
                                                                .getAuthorities();

        UsernamePasswordAuthenticationToken authentication = 
        new UsernamePasswordAuthenticationToken(busca.getNombre(), busca.getPassword(), nowAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return this.obtenerBuscaConectado();
    }

    @Override
    public void borrar(Long id) {
        /*Este método solo se llama cuando el propio usuario borra su cuenta, por lo que 
        se forzará un logout*/
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
    public Busca obtenerBuscaConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) return null;
        Busca busca = obtenerPorNombre(auth.getName());
        return busca;
    }

    @Override
    public Busca guardarBuscaDesdeNuevoUsuario(NuevoUsuario nuevoUsuario) {
        Busca busca = new Busca(nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getCiudad(), nuevoUsuario.getTelefono(), nuevoUsuario.getPassword());
        Busca busca2 = this.guardar(busca);
        
        return busca2;
    }

    @Override
    public boolean coincidePassword(String verificarPassword){
        return passwordEncoder.matches(verificarPassword, this.obtenerBuscaConectado().getPassword());
    }

    @Override
    public void cambiarPassword(String nuevoPassword) {
        Busca busca = this.obtenerBuscaConectado();
        busca.setPassword(nuevoPassword);
        this.guardarCambios(busca);
    }
    
    @Override
    public Boolean estaInscritoOferta(Long id){

        for(Oferta oferta: this.obtenerBuscaConectado().getListaOfertas()){
            if(oferta.getId() == id) return true;
        }

        return false;
    }

    @Override
    public BuscaView convertirBuscaABuscaView(Busca busca) {
        return new BuscaView(busca.getNombre(), busca.getEmail(), busca.getTelefono(), 
        busca.getCiudad(), busca.getListaConocimientos(), busca.getListaExperiencias());
    }

}
