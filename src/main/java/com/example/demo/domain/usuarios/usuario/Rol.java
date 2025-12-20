package com.example.demo.domain.usuarios.usuario;

public enum Rol {
    ADMIN,CONTRATA,BUSCA;

    public static Rol[] rolesSinAdmin(){
        Rol[] listaDef = new Rol[2];
        listaDef[0] = CONTRATA;
        listaDef[1] = BUSCA;
        return listaDef;
    }
}


