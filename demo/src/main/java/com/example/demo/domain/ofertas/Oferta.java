package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.usuarios.Contrata;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "listaCandidatos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Data
@Entity
@Table(name="ofertas")
public class Oferta implements Comparable<Oferta>{
    
    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Size(max = 30)
    private String puesto;

    @NotNull
    private String sector;

    @Nullable
    private String descripcion;

    @NotNull
    private String ciudad;

    @Size(max = 10)
    private List<String> requisitos;

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

    @ManyToOne
    @JoinColumn(name = "contrata_id")//Sospechoso de fallar
    private Contrata contrata;

    public Oferta(@NotNull @Size(max = 30) String puesto, @NotNull String sector, String descripcion,
            @NotNull String ciudad, @Size(max = 10) List<String> requisitos, Double salarioAnual,
            @NotNull TipoContrato tipoContrato, @NotNull Byte horas, @NotNull ModalidadTrabajo modalidadTrabajo,
            String nombreEmpresa, LocalDate fechaPublicacion, Contrata contrata) {
        this.puesto = puesto;
        this.sector = sector;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.requisitos = requisitos;
        this.salarioAnual = salarioAnual;
        this.tipoContrato = tipoContrato;
        this.horas = horas;
        this.modalidadTrabajo = modalidadTrabajo;
        this.nombreEmpresa = nombreEmpresa;
        this.fechaPublicacion = fechaPublicacion;
        this.contrata = contrata;
    }

    @Override
    public int compareTo(Oferta o1) {
        if(o1.getFechaPublicacion().isAfter(this.fechaPublicacion)) return 1;
        else if(o1.getFechaPublicacion() == this.fechaPublicacion) return 0;
        else return -1;
    }

    // @ManyToMany(mappedBy = "listaOfertas")
    // @JsonBackReference
    // @Nullable
    // private Set<Busca> listaCandidatos;

    
}
