package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.userRelated.Usuario;
import com.example.demo.repositories.UsuarioRepository;



@Component
public class UserDetailsImpl implements UserDetailsService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(username);
        if(usuario == null) throw new UsernameNotFoundException("Usuario no encontrado");
        return User
        .withUsername(username)
        .roles(usuario.getRol().toString())
        .password(usuario.getPassword())
        .build();
    }
}
