package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.BuscaRepository;
import com.example.demo.repositories.ContrataRepository;

@Component
public class UserDetailsImpl implements UserDetailsService {
    
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BuscaRepository buscaRepository;
    @Autowired
    ContrataRepository contrataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        if(adminRepository.findByNombre(username) != null){
            Admin admin = adminRepository.findByNombre(username);
            return User
            .withUsername(username)
            .roles(admin.getRol().toString())
            .password(admin.getPassword())
            .build();
        }
        else if(buscaRepository.findByNombre(username) != null){
            Busca busca = buscaRepository.findByNombre(username);
            return User
            .withUsername(username)
            .roles(busca.getRol().toString())
            .password(busca.getPassword())
            .build();
        }
        else if(contrataRepository.findByNombre(username) != null){
            Contrata contrata = contrataRepository.findByNombre(username);
            return User
            .withUsername(username)
            .roles(contrata.getRol().toString())
            .password(contrata.getPassword())
            .build();
        }
        else throw new UsernameNotFoundException("Usuario no encontrado");

    }
}

