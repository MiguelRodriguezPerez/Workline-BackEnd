package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initData(AdminService adminService,ContrataService contrataService, 
	// OfertaService ofertaService, BuscaService buscaService, 
	// ConocimientoService conocimientoService, ExperienciaService experienciaService){
	// 	return args ->{
	// 		// 1234kasdddddddjfA#
	// 		// 5678MMsdddddddjfA# 
	// 		// Iconos 24 *24

	// 		// contrataService.guardar(new Contrata("TechCorp", "techcorp@gmail.com", "Madrid", "623456789", "1234kasdddddddjfA#"));
	// 		// contrataService.guardar(new Contrata("InnovateCo", "innovateco@gmail.com", "Barcelona", "624567890", "1234kasdddddddjfA#"));
	// 		// contrataService.guardar(new Contrata("BuildIt", "buildit@gmail.com", "Sevilla", "625678901", "1234kasdddddddjfA#"));
	// 		// contrataService.guardar(new Contrata("GreenTech", "greentech@gmail.com", "Valencia", "626789012", "1234kasdddddddjfA#"));

	// 		// ofertaService.guardarOferta(new Oferta("Camarero", "Hosteleria", "Servir copas", "Jaen", 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Cocinero", "Hosteleria", "Preparar alimentos", "Jaen", 18000d, TipoContrato.TEMPORAL, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Repartidor", "Logistica", "Repartir productos", "Jaen", 20000d, TipoContrato.INDEFINIDO, (byte)35, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Recepcionista", "Hosteleria", "Atender clientes", "Malaga", 16000d, TipoContrato.DISCONTINUO, (byte)25, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Supervisor", "Industria", "Gestionar equipos", "Madrid", 30000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Vigilante", "Seguridad", "Vigilar instalaciones", "Sevilla", 22000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Jardinero", "Mantenimiento", "Cuidar jardines", "Cordoba", 19000d, TipoContrato.TEMPORAL, (byte)30, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Carpintero", "Construcción", "Hacer muebles", "Jaen", 21000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Abogado", "Legal", "Asesorar clientes", "Granada", 35000d, TipoContrato.INDEFINIDO, (byte)35, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Ingeniero", "Tecnología", "Desarrollar software", "Malaga", 50000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Dentista", "Salud", "Atender pacientes", "Madrid", 40000d, TipoContrato.INDEFINIDO, (byte)35, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Cajero", "Comercio", "Atender caja", "Jaen", 15000d, TipoContrato.TEMPORAL, (byte)20, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Peluquero", "Estética", "Cortar cabello", "Sevilla", 19000d, TipoContrato.TEMPORAL, (byte)30, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Mozo", "Almacén", "Cargar productos", "Madrid", 16000d, TipoContrato.TEMPORAL, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Dependienta", "Comercio", "Atender clientes", "Granada", 17000d, TipoContrato.TEMPORAL, (byte)20, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Auxiliar", "Administrativo", "Gestión de oficinas", "Jaen", 19000d, TipoContrato.TEMPORAL, (byte)35, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
	// 		// ofertaService.guardarOferta(new Oferta("Barista", "Hosteleria", "Preparar cafés", "Malaga", 16000d, TipoContrato.TEMPORAL, (byte)20, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Taxista", "Transporte", "Transportar personas", "Sevilla", 20000d, TipoContrato.TEMPORAL, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Director", "Administración", "Gestionar oficina", "Granada", 40000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Técnico", "Tecnología", "Reparar ordenadores", "Madrid", 23000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(1)));
	// 		// ofertaService.guardarOferta(new Oferta("Estilista", "Estética", "Asesorar imagen", "Jaen", 22000d, TipoContrato.INDEFINIDO, (byte)35, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Mensajero", "Transporte", "Llevar paquetes", "Malaga", 16000d, TipoContrato.TEMPORAL, (byte)30, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Limpiador", "Mantenimiento", "Limpiar oficinas", "Sevilla", 14000d, TipoContrato.TEMPORAL, (byte)30, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Contable", "Finanzas", "Gestionar cuentas", "Madrid", 28000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO,contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Arquitecto", "Construcción", "Diseñar edificios", "Granada", 45000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Administrador", "Tecnología", "Gestionar servidores", "Malaga", 36000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Programador", "Tecnología", "Desarrollar software", "Jaen", 38000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Asesor", "Consultoría", "Dar consejos empresariales", "Sevilla", 29000d, TipoContrato.TEMPORAL, (byte)35, ModalidadTrabajo.HIBRIDO,contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Fotógrafo", "Creativo", "Tomar fotos", "Madrid", 25000d, TipoContrato.TEMPORAL, (byte)25, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Locutora", "Medios", "Leer anuncios", "Granada", 22000d, TipoContrato.TEMPORAL, (byte)20, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Chef", "Hosteleria", "Cocinar platillos", "Jaen", 24000d, TipoContrato.TEMPORAL, (byte)30, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Tatuador", "Estética", "Realizar tatuajes", "Sevilla", 26000d, TipoContrato.TEMPORAL, (byte)35, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Pintor", "Construcción", "Pintar paredes", "Madrid", 23000d, TipoContrato.TEMPORAL, (byte)40, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Gestor", "Administración", "Gestionar operaciones", "Jaen", 21000d, TipoContrato.INDEFINIDO, (byte)40, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(2)));
	// 		// ofertaService.guardarOferta(new Oferta("Tutor", "Educación", "Dar clases", "Malaga", 20000d, TipoContrato.TEMPORAL, (byte)20, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(2)));


	// 		// buscaService.guardar(new Busca("roberto","manuel@gmail.com","Cordoba","654344512","1234kasdddddddjfA#"));
	// 		// buscaService.guardar(new Busca("manuel","manuel@gmail.com","Soria","654344512","1234kasdddddddjfA#"));

	// 		// conocimientoService.guardarConocimientoDemoApp(buscaService.obtenerTodos().get(0), new Conocimiento(0l, "Liceo Maria", "Barrendero", LocalDate.of(2006, 10, 11), LocalDate.of(2008, 11, 12), buscaService.obtenerTodos().get(0)));
	// 		// conocimientoService.guardarConocimientoDemoApp(buscaService.obtenerTodos().get(0), new Conocimiento(0l, "Escuela Central", "Electricista", LocalDate.of(2010, 5, 15), LocalDate.of(2013, 5, 14), buscaService.obtenerTodos().get(0)));
	// 		// conocimientoService.guardarConocimientoDemoApp(buscaService.obtenerTodos().get(0), new Conocimiento(0l, "Academia del Mar", "Navegante", LocalDate.of(2015, 1, 10), LocalDate.of(2018, 1, 9), buscaService.obtenerTodos().get(0)));

	// 		// conocimientoService.guardarConocimientoDemoApp(buscaService.obtenerTodos().get(1), new Conocimiento(0l, "Liceo Maria", "Barrendero", LocalDate.of(2006, 10, 11), LocalDate.of(2008, 11, 12), buscaService.obtenerTodos().get(1)));
	// 		// conocimientoService.guardarConocimientoDemoApp(buscaService.obtenerTodos().get(1), new Conocimiento(0l, "Escuela Central", "Electricista", LocalDate.of(2010, 5, 15), LocalDate.of(2013, 5, 14), buscaService.obtenerTodos().get(1)));
	// 		// conocimientoService.guardarConocimientoDemoApp(buscaService.obtenerTodos().get(1), new Conocimiento(0l, "Academia del Mar", "Navegante", LocalDate.of(2015, 1, 10), LocalDate.of(2018, 1, 9), buscaService.obtenerTodos().get(1)));

	// 		// experienciaService.guardarExperienciaDemoApp(buscaService.obtenerTodos().get(1), new Experiencia(0l, "Camarero", "AirEuropa", LocalDate.of(2008, 10, 11), LocalDate.of(2010, 10, 02), buscaService.obtenerTodos().get(1)));
	// 		// experienciaService.guardarExperienciaDemoApp(buscaService.obtenerTodos().get(1), new Experiencia(0l, "Zapatero", "Rebook", LocalDate.of(2014, 10, 11), LocalDate.of(2018, 10, 02), buscaService.obtenerTodos().get(1)));
	// 		// experienciaService.guardarExperienciaDemoApp(buscaService.obtenerTodos().get(1), new Experiencia(0l, "Carpintero", "Melinosa", LocalDate.of(2017, 3, 15), LocalDate.of(2020, 3, 14), buscaService.obtenerTodos().get(1)));

	// 		// experienciaService.guardarExperienciaDemoApp(buscaService.obtenerTodos().get(0), new Experiencia(0l, "Desarrollador Backend", "Innovatech", LocalDate.of(2016, 5, 20), LocalDate.of(2019, 5, 19), buscaService.obtenerTodos().get(0)));
	// 		// experienciaService.guardarExperienciaDemoApp(buscaService.obtenerTodos().get(0), new Experiencia(0l, "Diseñador Gráfico", "CreativeWorks", LocalDate.of(2014, 2, 10), LocalDate.of(2017, 2, 9), buscaService.obtenerTodos().get(0)));
	// 		// experienciaService.guardarExperienciaDemoApp(buscaService.obtenerTodos().get(0), new Experiencia(0l, "Administrador de Proyectos", "TechnoCorp", LocalDate.of(2019, 11, 1), LocalDate.of(2022, 10, 31), buscaService.obtenerTodos().get(0)));

			
	// 	};
	// }

}
