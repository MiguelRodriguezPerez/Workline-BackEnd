package com.example.demo.services.usuarios;

import java.util.List;

import com.example.demo.domain.usuarios.Admin;

public interface AdminService {
    Admin guardar(Admin admin);
    Admin guardarSinEncriptar(Admin admin);
    void borrar(Long id);
    Admin editar(Admin admin);
    List <Admin> obtenerTodos();
    Admin obtenerPorId(Long id);
    Admin obtenerPorNombre(String nombre);
    String obtenerNombre();
    Admin obtenerAdminConectado();
}
