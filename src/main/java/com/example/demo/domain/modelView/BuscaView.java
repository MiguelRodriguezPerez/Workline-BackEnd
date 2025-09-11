package com.example.demo.domain.modelView;

import java.util.List;
import java.util.Set;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*Necesitas esta clase para que cuando un contrata mire un candidato en la vista
VerCandidatoPage no tenga acceso a su contraseña ni a su lista de ofertas.
No lo he llamado BuscaDto porque igual necesito esa clase para la creación de usuarios*/
public class BuscaView {
    
    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;
    private Set<Conocimiento> listaConocimientos;
    private Set<Experiencia> listaExperiencias;
}
