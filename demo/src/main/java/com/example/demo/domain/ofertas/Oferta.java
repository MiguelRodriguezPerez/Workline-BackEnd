package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
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

@ToString(exclude = {"listaCandidatos", "contrata"})
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

    @ManyToMany(mappedBy = "listaOfertas")
    @JsonBackReference(value = "busca-oferta")
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
