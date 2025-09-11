package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.domain.usuarios.UsuarioDto;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@Service
public class UsuarioService {

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    ConocimientoService conocimientoService;

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    AdminService adminService;

    @Autowired
    OfertaService ofertaService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario encontrarUsuarioPorNombre(String nombre) {

        if (contrataService.obtenerPorNombre(nombre) != null)
            return contrataService.obtenerPorNombre(nombre);
        else if (buscaService.obtenerPorNombre(nombre) != null)
            return buscaService.obtenerPorNombre(nombre);
        else if (adminService.obtenerPorNombre(nombre) != null)
            return adminService.obtenerPorNombre(nombre);
        else
            return null;

    }

    public boolean esNombreRepetido(String nombre) {
        return this.encontrarUsuarioPorNombre(nombre) != null;
    }

    public UsuarioContext convertirUsuarioAUsuarioView(Usuario usuario) {
        return new UsuarioContext(usuario.getNombre(), usuario.getEmail(), usuario.getRol().toString());
    }

    public UsuarioDto convertirUsuarioAUsuarioDto(Usuario usuario) {
        return new UsuarioDto(usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getCiudad());
    }

    public Usuario obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUsuario = this.encontrarUsuarioPorNombre(authentication.getName());
        return currentUsuario;
    }

    public Usuario guardarCambios(UsuarioDto usuarioDto) {

        Usuario currentUsuario = this.obtenerUsuarioLogueado();

        currentUsuario.setNombre(usuarioDto.getNombre());
        currentUsuario.setEmail(usuarioDto.getEmail());
        currentUsuario.setTelefono(usuarioDto.getTelefono());
        currentUsuario.setCiudad(usuarioDto.getCiudad());

        switch (currentUsuario.getRol()) {

            case CONTRATA:
                Contrata contrata = (Contrata) currentUsuario;
                contrataService.guardarSinEncriptar(contrata);
                ofertaService.cambiarPropiedadOfertas(contrata.getListaOfertas(), contrata.getNombre());
                return contrata;
            case BUSCA:
                Busca busca = (Busca) currentUsuario;
                buscaService.guardarSinEncriptar(busca);
                return busca;
        }

        return null;

    }

    public void borrarCuentaUsuarioLogueado() {
        Usuario usuarioConectado = this.obtenerUsuarioLogueado();

        switch (usuarioConectado.getRol()) {
            case BUSCA:
                ofertaService.borrarBuscaDeTodasLasOfertas((Busca) usuarioConectado);
                conocimientoService.borrarTodosPorBusca(usuarioConectado.getId());
                experienciaService.borrarTodosPorBusca(usuarioConectado.getId());
                buscaService.borrarCuentaWrapper();
                break;
            case CONTRATA:
                ofertaService.borrarTodosCandidatosTodasOfertasFromContrataId((Contrata) usuarioConectado);
                ofertaService.borrarTodasLasOfertasDeUnContrata((Contrata) usuarioConectado);
                contrataService.borrarContrata(usuarioConectado.getId());
                break;
        }
    }


    /*Este método sirve para comprobar si la contraseña del usuario que esta logueado coincide
    con la que el propio usuario logueado esta introduciendo. Esta función se utiliza cuando el 
    usuario desea cambiar su contraseña*/
    public boolean comprobarPasswordUsuarioLogueado(String comprobar){
        /*usuarioConectado te esta devolviendo bien la contraseña encriptada. 
        No es problema de la clase padre */
        Usuario usuarioConectado = this.obtenerUsuarioLogueado();

        return passwordEncoder.matches(comprobar, usuarioConectado.getPassword());
    }

    public Usuario cambiarPasswordWrapper(String newPassword){
        Usuario usuarioConectado = this.obtenerUsuarioLogueado();
        usuarioConectado.setPassword(newPassword);

        switch (usuarioConectado.getRol()) {
            case BUSCA:
                Busca newBusca = buscaService.guardar((Busca) usuarioConectado);
                return newBusca;
            case CONTRATA:
                Contrata newContrata = contrataService.guardar((Contrata) usuarioConectado);
                return newContrata;
                
            default: return null;
        }
    }

    /*Cuando enviabas tu contraseña para cambiarla, el lado cliente le añadía " al principio y final
    del string password que provocaba que el método que comparaba las contraseñas diera como falso
    una contraseña que se envío bien.
    Este método sirve para quitar dichas contraseñas y que el método que comprueba si una contraseña
    es correcta de true*/
    public String quitarComillasPassword(String oldPassword){
        return oldPassword.replace("\"", "");
    }

}
