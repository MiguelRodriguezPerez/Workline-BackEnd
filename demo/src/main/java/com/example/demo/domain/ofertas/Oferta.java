package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"listaCandidatos", "contrata"}) // Excluir las colecciones relacionadas
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "ofertas")

public class Oferta implements Comparable<Oferta> {
    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Size(max = 30)
    private String puesto;

    @NotNull
    @Size(max = 25)
    private String sector;

    @Nullable
    @Size(max = 80)
    private String descripcion;

    @NotNull
    private String ciudad;

    @Nullable
    private Double salarioAnual;

    @NotNull
    private TipoContrato tipoContrato;

    @NotNull
    private Byte horas;

    @NotNull
    private ModalidadTrabajo modalidadTrabajo;

    private String nombreEmpresa;

    private LocalDate fechaPublicacion;

    @JsonManagedReference("busca-oferta") 
    @ManyToMany(mappedBy = "listaOfertas", fetch = FetchType.EAGER) 
    private List<Busca> listaCandidatos;

    @ManyToOne
    @JoinColumn(name = "contrata_id")
    @JsonBackReference("contrata-oferta")
    private Contrata contrata;

    @Override
    public int compareTo(Oferta o1) {
        if(o1.getFechaPublicacion().isAfter(this.fechaPublicacion)) return 1;
        else if(o1.getFechaPublicacion() == this.fechaPublicacion) return 0;
        else return -1;
    }

    public Oferta(@NotNull @Size(max = 30) String puesto, @NotNull String sector, @Size(max = 80) String descripcion,
                  @NotNull String ciudad, Double salarioAnual, @NotNull TipoContrato tipoContrato, @NotNull Byte horas,
                  @NotNull ModalidadTrabajo modalidadTrabajo, Contrata contrata) {
        this.puesto = puesto;
        this.sector = sector;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.salarioAnual = salarioAnual;
        this.tipoContrato = tipoContrato;
        this.horas = horas;
        this.modalidadTrabajo = modalidadTrabajo;
        this.fechaPublicacion = LocalDate.now();
        this.nombreEmpresa = contrata.getNombre();
        this.contrata = contrata;
    }

    public Oferta(String puesto2, String sector2, String descripcion2, String ciudad2, Double salarioAnual2, TipoContrato t1, Byte horas2, ModalidadTrabajo m1) { 
        this.puesto = puesto2; 
        this.sector = sector2; 
        this.descripcion = descripcion2; 
        this.ciudad = ciudad2; 
        this.salarioAnual = salarioAnual2; 
        this.tipoContrato = t1; 
        this.horas = horas2; 
        this.modalidadTrabajo = m1; 
        this.fechaPublicacion = LocalDate.now();
        this.nombreEmpresa = ""; 
    }
}