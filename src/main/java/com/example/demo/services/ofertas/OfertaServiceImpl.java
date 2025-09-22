package com.example.demo.services.ofertas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoApi;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    OfertaRepository repo;

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    BusquedaOfertaService busquedaOfertaService;

    @Override
    public Oferta guardarOferta(Oferta oferta) {
        return repo.save(oferta);
    }

    @Override
    public Oferta guardarOfertaFromContrata(Oferta oferta) {

        // Sospechoso de fallar con jwt
        Contrata contrataConectado = contrataService.obtenerContrataConectado();

        oferta.setNombreEmpresa(contrataConectado.getNombre());
        oferta.setContrata(contrataConectado);

        /*
         * Como este método sirve para guardar nuevas ofertas, pero también sirve
         * para editar las ofertas, se comprueba si la fecha es nula para evitar que en
         * caso de que se edite una oferta la fecha no cambie
         */
        if (oferta.getFechaPublicacion() == null)
            oferta.setFechaPublicacion(LocalDate.now());

        this.guardarOferta(oferta);
        contrataConectado.getListaOfertas().add(oferta);
        contrataService.guardarSinEncriptar(contrataConectado);

        return oferta;
    }

    @Override
    public Oferta guardarCambios(Oferta oferta) {
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
    public void borrarTodosCandidatosTodasOfertasFromContrataId(Contrata contrata) {
        repo.deleteAllCandidatosFromContrataId(contrata.getId());
    }

    /*
     * Necesitas este método para garantizar que en la sesión ambas entidades de la
     * relación se borren
     */
    @Override
    public void borrarOfertaWrapper(Long id) {
        Oferta oferta = this.obtenerPorId(id);

        if (!oferta.getListaCandidatos().isEmpty())
            repo.removeAllCandidatesFromOferta(id);

        /* No tienes que borrar ambos lados de las relaciones si usas sql "directo" */
        repo.deleteById(id);
    }

    @Override
    public void borrarTodosCandidatosDeUnaOferta(Long id) {
        repo.removeAllCandidatesFromOferta(id);
    }

    @Override
    public void borrarBuscaDeTodasLasOfertas(Busca busca) {
        repo.deleteBuscaFromAllOfertas(busca.getId());
    }

    @Override
    public void borrarTodasLasOfertasDeUnContrata(Contrata contrata) {
        repo.deleteAllOfertasByContrataId(contrata.getId());
    }

    @Override
    public Oferta obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Page<Oferta> obtenerPaginaApi(int numPag, BusquedaOferta busquedaOferta) {
        final Integer ofertasPorPagina = 10;

        List<Oferta> resultadosBusqueda = new ArrayList<>();
        /* Si no lo pasas a ArrayList, no podrás tener índices con los que realizar la paginación */
        resultadosBusqueda.addAll(this.obtenerResultados(busquedaOferta));
        if (resultadosBusqueda.isEmpty()) return null;

        Pageable paginable = PageRequest.of(numPag, ofertasPorPagina);
        int primeraOferta = (int) paginable.getOffset();
        int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(), resultadosBusqueda.size());

        Page<Oferta> resultado = new PageImpl<>(resultadosBusqueda.subList(primeraOferta, ultimaOferta), paginable,
                resultadosBusqueda.size());
        return resultado;
    }

    @Value("${spring.datasource.url}")
    private String fullRoute;

    @Override
    public Set<Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        /* WHERE 1=1 te permite poder añadir AND sin tener que preocuparte 
        de que la consulta rompa si no recibe ningún criterio */
        StringBuilder query = new StringBuilder("SELECT * FROM oferta WHERE 1=1"); 
        Set<Oferta> resultado = new HashSet<>();
        List<Object> params = new ArrayList<>(); 
        Dotenv dotenv = Dotenv.configure().load();
        

        if (busquedaOferta.getPuesto() != null && !busquedaOferta.getPuesto().isEmpty()) {
            query.append(" AND puesto = ?");
            params.add(busquedaOferta.getPuesto());
        }

        if (busquedaOferta.getTipoContrato() != null && !busquedaOferta.getTipoContrato().isEmpty()) {
            query.append(" AND tipo_contrato = ?");
            params.add(busquedaOferta.getTipoContrato());
        }

        if (busquedaOferta.getCiudad() != null && !busquedaOferta.getCiudad().isEmpty()) {
            query.append(" AND ciudad = ?");
            params.add(busquedaOferta.getCiudad());
        }

        if (busquedaOferta.getSalarioAnualMinimo() != null && busquedaOferta.getSalarioAnualMinimo() != 0) {
            query.append(" AND salario_anual >= ?");
            params.add(busquedaOferta.getSalarioAnualMinimo());
        }

        if (busquedaOferta.getModalidad() != null && !busquedaOferta.getModalidad().isEmpty()) {
            query.append(" AND modalidad = ?");
            params.add(busquedaOferta.getModalidad());
        }

        /* Conexión a la bd */
        
        try {
            Connection connection = DriverManager.getConnection (
                fullRoute, 
                dotenv.get("MYSQLUSER"), 
                dotenv.get("MYSQLPASSWORD")
            );

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
            /* Añade dinámicamente los parámetros a la query (Evita sql injection) */
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i)); 
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultado.add(this.mapFromResultSet(resultSet));
            }
            
        }
                   
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return resultado;
    }

    /* TODO: Realizar este método por consulta precompilada */


    public Oferta mapFromResultSet(ResultSet resultSet) throws SQLException {
       try {
            Oferta oferta = new Oferta(
                resultSet.getString("puesto"),
                resultSet.getString("sector"),
                resultSet.getString("descripcion"),
                resultSet.getString("ciudad"),
                resultSet.getObject("salario_anual", Double.class),
                TipoContrato.fromInt(resultSet.getInt("tipo_contrato")),
                resultSet.getByte("horas"),
                ModalidadTrabajo.fromInt(resultSet.getInt("modalidad_trabajo"))
            );

            // Asignamos directamente los campos adicionales
            oferta.setId(resultSet.getLong("id"));
            oferta.setNombreEmpresa(resultSet.getString("nombre_empresa"));

            return oferta;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null; // o lanza una excepción según tu lógica
        }

        
    }

    @Override
    public void cambiarPropiedadOfertas(Set<Oferta> listaOfertas, String username) {
        for (Oferta oferta : listaOfertas) {
            oferta.setNombreEmpresa(username);
            this.guardarCambios(oferta);
        }
    }

    @Override
    public boolean estaSuscritoOferta(Long id) {
        if (buscaService.obtenerBuscaConectado() != null
                && this.obtenerPorId(id) != null
                && buscaService.obtenerBuscaConectado().getListaOfertas().contains(this.obtenerPorId(id))) {
            return true;
        }
        return false;
    }

    @Override
    public Oferta convertirOfertaDtoApiAOferta(OfertaDtoApi ofertaDtoApi) {

        TipoContrato t1 = null;
        ModalidadTrabajo m1 = null;

        for (ModalidadTrabajo m : ModalidadTrabajo.values()) {
            if (ofertaDtoApi.getModalidadTrabajo().equalsIgnoreCase(m.toString()))
                m1 = m;
        }

        for (TipoContrato t : TipoContrato.values()) {
            if (ofertaDtoApi.getTipoContrato().equalsIgnoreCase(t.toString()))
                t1 = t;
        }

        Oferta resultado = new Oferta(ofertaDtoApi.getPuesto(),
                ofertaDtoApi.getSector(),
                ofertaDtoApi.getDescripcion(),
                ofertaDtoApi.getCiudad(),
                ofertaDtoApi.getSalarioAnual(),
                t1,
                ofertaDtoApi.getHoras(),
                m1);

        System.out.println(resultado + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

        return resultado;
    }

    @Override
    public void inscribirBuscaConectadoWrapper(Long id) {
        Busca buscaConectado = buscaService.obtenerBuscaConectado();
        Oferta oferta = this.obtenerPorId(id);

        oferta.getListaCandidatos().add(buscaConectado);
        buscaConectado.getListaOfertas().add(oferta);

        this.guardarOferta(oferta);
        buscaService.guardarSinEncriptar(buscaConectado);
    }

    @Override
    public void desinscribirBuscaConectadoWrapper(Long id) {
        Busca buscaConectado = buscaService.obtenerBuscaConectado();
        Oferta oferta = this.obtenerPorId(id);

        // Sospechoso de fallar
        oferta.getListaCandidatos().remove(buscaConectado);
        buscaConectado.getListaOfertas().remove(oferta);

        this.guardarOferta(oferta);
        buscaService.guardarSinEncriptar(buscaConectado);
    }

    @Override
    public int obtenerNumeroCandidatos(Long id) {
        Oferta oferta = this.obtenerPorId(id);
        return oferta.getListaCandidatos().size();
    }

}
