package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.usuarios.Admin;
import com.example.demo.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Admin guardar(Admin admin) {
       passwordEncoder.encode(admin.getPassword());
       return repo.save(admin);
    }

    @Override
    public Admin guardarSinEncriptar(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarSinEncriptar'");
    }

    @Override
    public void borrar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

    @Override
    public Admin editar(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public List<Admin> obtenerTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodos'");
    }

    @Override
    public Admin obtenerPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public Admin obtenerPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorNombre'");
    }

    @Override
    public String obtenerNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerNombre'");
    }

    @Override
    public Admin obtenerAdminConectado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerAdminConectado'");
    }
    
}
