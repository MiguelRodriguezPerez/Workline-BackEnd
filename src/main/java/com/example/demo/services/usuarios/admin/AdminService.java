package com.example.demo.services.usuarios.admin;

import java.util.List;

import com.example.demo.domain.usuarios.Admin;

public interface AdminService {
    Admin guardar(Admin admin);
    Admin guardarSinEncriptar(Admin admin);
    Admin guardarCambios(Admin admin);
    void borrar(Long id);
    List <Admin> obtenerTodos();
    Admin obtenerPorId(Long id);
    Admin obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);
    String obtenerNombre();
    Admin obtenerAdminConectado();
}
