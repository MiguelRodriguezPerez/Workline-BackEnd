package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Size(max = 80)
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
        this.contrata = contrata;

        this.nombreEmpresa = contrata.getNombre();
        this.fechaPublicacion = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "contrata_id")//Sospechoso de fallar
    private Contrata contrata;

    @Override
    public int compareTo(Oferta o1) {
        if(o1.getFechaPublicacion().isAfter(this.fechaPublicacion)) return 1;
        else if(o1.getFechaPublicacion() == this.fechaPublicacion) return 0;
        else return -1;
    }

    public String convertirMayus(String s){
        s = s.toLowerCase();
        s = Character.toString(s.charAt(0)).toUpperCase() + s.substring(1);
        return s;
    }

    public String parsearTipoContrato(){
        return convertirMayus(this.tipoContrato.toString());
    }

    public String parsearModalidadTrabajo(){
        return convertirMayus(this.modalidadTrabajo.toString());
    }

    public String parsearFecha(){
        LocalDate l = this.fechaPublicacion;
        String resultado = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(l).toString();
        resultado = resultado.replace('-', '/');
        
        return resultado;
    }

    // @ManyToMany(mappedBy = "listaOfertas")
    // @JsonBackReference
    // @Nullable
    // private Set<Busca> listaCandidatos;

    
}
