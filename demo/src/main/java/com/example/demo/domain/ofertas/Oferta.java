package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

@ToString(exclude = {"contrata"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Data
@Entity
@Table(name="ofertas")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Oferta implements Comparable<Oferta>{
    
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

    /*requisito y valorable será el valor que se introducirá en el input text,
    luego habrá un postMapping que obtendrá el valor de ese input, lo añadirá 
    a su lista correspondiente y se borrará después del campo del input*/

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

    @ManyToMany(mappedBy = "listaOfertas", fetch = FetchType.EAGER)
    @Nullable
    private List<Busca> listaCandidatos;

    @ManyToOne
    @JoinColumn(name = "contrata_id")//Sospechoso de fallar
    @JsonBackReference
    private Contrata contrata;

    /*Este constructor esta diseñado para recibir ofertasDtoApi.
    Los campos que queden nulos (contrata y nombreEmpresa) los gestiona el método
    ofertaService.guardarOfertaFromContrata()*/
    public Oferta(@NotNull @Size(max = 30) String puesto, @NotNull String sector, @Size(max = 80) String descripcion,
            @NotNull String ciudad, Double salarioAnual, @NotNull TipoContrato tipoContrato, @NotNull Byte horas,
            @NotNull ModalidadTrabajo modalidadTrabajo) {
        this.puesto = puesto;
        this.sector = sector;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.salarioAnual = salarioAnual;
        this.tipoContrato = tipoContrato;
        this.horas = horas;
        this.modalidadTrabajo = modalidadTrabajo;
        this.fechaPublicacion = LocalDate.now();
    }

    // @PreRemove Esta anotación define una función a ejecutar antes de borrar esta instancia de entidad
    // public void preRemove(){
    //     setContrata(null);
    // }

    

    @Override
    public int compareTo(Oferta o1) {
        if(o1.getFechaPublicacion().isAfter(this.fechaPublicacion)) return 1;
        else if(o1.getFechaPublicacion() == this.fechaPublicacion) return 0;
        else return -1;
    }



    //Constructor para el commandLineRunner
    public Oferta(@NotNull @Size(max = 30) String puesto, @NotNull String sector, @Size(max = 80) String descripcion,
            @NotNull String ciudad, Double salarioAnual, @NotNull TipoContrato tipoContrato, @NotNull Byte horas,
            @NotNull ModalidadTrabajo modalidadTrabajo,Contrata contrata) {
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

    

    
}
