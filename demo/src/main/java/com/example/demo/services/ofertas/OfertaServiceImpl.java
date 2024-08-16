package com.example.demo.services.ofertas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.exceptions.PagOfertasPublicadasIncorrecta;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.services.usuarios.ContrataService;

@Service
public class OfertaServiceImpl implements OfertaService{

    @Autowired
    OfertaRepository repo;

    @Autowired
    ContrataService contrataService;

    @Autowired
    BusquedaOfertaService busquedaOfertaService;

    @Override
    public Oferta guardarOferta(Oferta oferta) {
        return repo.save(oferta);
    }

    @Override
    public Oferta guardarOfertaFromContrata(Oferta oferta){
        Contrata contrataConectado = contrataService.obtenerContrataConectado();

        oferta.setNombreEmpresa(contrataConectado.getNombre());
        oferta.setContrata(contrataConectado);

        /*Como este método sirve para guardar nuevas ofertas, pero también sirve
        para editar las ofertas, se comprueba si la fecha es nula para evitar que en caso
        de que se edite una oferta la fecha no cambie*/
        if(oferta.getFechaPublicacion() == null) oferta.setFechaPublicacion(LocalDate.now());

        this.guardarOferta(oferta);
        contrataConectado.getListaOfertas().add(oferta);
        contrataService.guardarSinEncriptar(contrataConectado);

        return oferta;
    }

    @Override
    public Oferta guardarCambios(Oferta oferta){
        Oferta ofertaEdit = this.obtenerPorId(oferta.getId());

        ofertaEdit.setPuesto(oferta.getPuesto());
        ofertaEdit.setSector(oferta.getSector());
        ofertaEdit.setModalidadTrabajo(oferta.getModalidadTrabajo());
        ofertaEdit.setDescripcion(oferta.getDescripcion());
        ofertaEdit.setSalarioAnual(oferta.getSalarioAnual());
        ofertaEdit.setTipoContrato(oferta.getTipoContrato());
        ofertaEdit.setHoras(oferta.getHoras());
        ofertaEdit.setFechaPublicacion(oferta.getFechaPublicacion());
        ofertaEdit.setCiudad(oferta.getCiudad());

        return this.guardarOferta(ofertaEdit);        
    }

    @Override
    public void borrarOferta(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Oferta obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Oferta> obtenerTodos() {
        ArrayList<Oferta> resultado = (ArrayList<Oferta>)repo.findAll();
        Collections.sort(resultado,(f1,f2) -> f1.getFechaPublicacion().compareTo(f2.getFechaPublicacion()));
        return resultado;
    }

    @Override
    public List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        ArrayList<Oferta> listaResultado = new ArrayList<>(repo.findAll());
        // System.out.println(listaResultado);
        // System.out.println(busquedaOferta + "AAAAAAAAAAAAAAAAAAAAAAAA");

        /*NOTA: Al filtrar cada campo, la primera condición tiene que ser que ese
        campo no sea null para evitar una nullPointerException*/

        /*Originalmente filtrabas las ofertas con varios removeIf, pero esto realizaba demasiados bucles,
        y en mi opinión, los streams son ilegibles, por lo que use un iterator que funciona de la siguiente manera.
        Por cada oferta en la lista, el iterador obtiene esa oferta y declara un valor booleano false para determinar
        si esta oferta se borra de la lista de resultados o no. Después cada if en la misma vuelta evalúa si se
        cumple cada condición de la búsqueda y, en caso negativo, cambia el valor booleano eliminar a true para evitar
        que se siga evaluando la oferta y confirmar al final del bucle que la oferta debe ser borrada de los resultados*/

        Iterator<Oferta> iter = listaResultado.iterator();

        while (iter.hasNext()) {
            Oferta o = iter.next();
            boolean eliminar = false;
        
            if (busquedaOferta.getPuestoB() != null && !busquedaOferta.getPuestoB().isEmpty()
                && !o.getPuesto().equals(busquedaOferta.getPuestoB())) eliminar = true;

        
            // Segunda condición: Filtrar por SectorB
            if (!eliminar && busquedaOferta.getSectorB() != null && !busquedaOferta.getSectorB().equals("placeholder")
                && !busquedaOferta.getSectorB().isEmpty()
                && !o.getSector().equals(busquedaOferta.getSectorB())) eliminar = true;
 
        
            // Tercera condición: Filtrar por TipoContratoB
            if (!eliminar && busquedaOferta.getTipoContratoB() != null && !busquedaOferta.getTipoContratoB().isEmpty()
                && !o.getTipoContrato().toString().toUpperCase().equals(busquedaOferta.getTipoContratoB().toUpperCase())) { // PELIGRO COMPARANDO ENUMS
                eliminar = true;
            }
        
            // Cuarta condición: Filtrar por CiudadB
            if (!eliminar && busquedaOferta.getCiudadB() != null && !busquedaOferta.getCiudadB().isEmpty()
                    && !o.getCiudad().equals(busquedaOferta.getCiudadB())) eliminar = true;
         
        
            // Quinta condición: Filtrar por SalarioAnual
            if (!eliminar && busquedaOferta.getSalarioAnual() != null
                    && o.getSalarioAnual() < busquedaOferta.getSalarioAnual()) eliminar = true;

        
            // Sexta condición: Filtrar por ModalidadB
            if (!eliminar && busquedaOferta.getModalidadB() != null && !busquedaOferta.getModalidadB().isEmpty()
                    && !o.getModalidadTrabajo().toString().equalsIgnoreCase(busquedaOferta.getModalidadB())) eliminar = true;

        
            // Séptima condición: Comprobar si los estudios requeridos coinciden
            if (!eliminar && busquedaOferta.getRequisitos() != null
                    && !coincidenEstudios(o, busquedaOferta)) eliminar = true;

        

            if (eliminar) iter.remove();

        }
        

        Collections.sort(listaResultado,(f1,f2) -> f1.getFechaPublicacion().compareTo(f2.getFechaPublicacion()));
        
        return listaResultado;
    }

    private final Integer ofertasPorPagina = 10;

    @Override
    public List<Oferta> obtenerPagina(Integer numeroPag, BusquedaOferta busquedaOferta) {
        
        if(numeroPag < 0  ||
         numeroPag > this.obtenerNumeroPaginas(busquedaOferta)) throw new PagOfertasPublicadasIncorrecta();
        
        Pageable paginable = PageRequest.of(numeroPag,ofertasPorPagina);
        
        if(busquedaOferta == null){
            Page<Oferta> resultado = repo.findAll(paginable);
            return resultado.getContent();
        }
        else{
            List<Oferta> listaOfertas = this.obtenerResultados(busquedaOferta);
            if(listaOfertas.isEmpty()) return null;

            int primeraOferta = (int) paginable.getOffset();
            int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(),listaOfertas.size());

            Page<Oferta> resultado = new PageImpl<>(listaOfertas.subList(primeraOferta, ultimaOferta),paginable,listaOfertas.size());

            // if(resultado.hasContent()) 
            return resultado.getContent();
        }
    }

    @Override
    public int obtenerNumeroPaginas(BusquedaOferta busquedaOferta) {
        if(busquedaOferta == null){
            Pageable paginable = PageRequest.of(0,ofertasPorPagina);
            Page<Oferta> resultado = repo.findAll(paginable);

            return resultado.getTotalPages();
        }

        Pageable paginable = PageRequest.of(0,ofertasPorPagina);
        List<Oferta> listaOfertas = obtenerResultados(busquedaOferta);

        int primeraOferta = (int) paginable.getOffset();
        int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(),listaOfertas.size());

        Page<Oferta> resultado = new PageImpl<>(listaOfertas.subList(primeraOferta, ultimaOferta),paginable,listaOfertas.size());

        return resultado.getTotalPages();
    }


    @Override
    public boolean existeSiguientePagina(Integer paginaElecta, String busquedaOferta) {
        if(busquedaOferta == null){
            if(obtenerNumeroPaginas(null) -1 > paginaElecta) return true;
            else return false;
        }
        else{
            busquedaOfertaService.obtenerBusquedaDesdeUrl(busquedaOferta);
            return false;
        }
    }

    @Override
    public int existeAnteriorPagina(Integer paginaElecta) {
        if(paginaElecta>0){
            paginaElecta--;
            return paginaElecta;
        }
        else return paginaElecta;
    }
   

    @Override
    public void cambiarPropiedadOfertas(List<Oferta> listaOfertas, String username) {
        for(Oferta oferta: listaOfertas){
            oferta.setNombreEmpresa(username);
            this.guardarCambios(oferta);
        }
    }

    @Override
    public void borrarCandidatosOferta(Long id) {
        
    }

    @Override
    public List<Integer> obtenerListaPaginas(BusquedaOferta busquedaOferta,Integer numPag) {
        Integer totalPaginas = this.obtenerNumeroPaginas(busquedaOferta);
        return null;
    }
    
}
