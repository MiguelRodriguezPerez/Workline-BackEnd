package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.usuarios.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Usuario findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
