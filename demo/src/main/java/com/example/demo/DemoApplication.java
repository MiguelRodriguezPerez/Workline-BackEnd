package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.ContrataService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ContrataService contrataService, OfertaService ofertaService){
		return args ->{

			// ofertaService.guardarOferta(new Oferta("Camarero", "Hosteleria", "Servir copas", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.PRESENCIAL,
			// LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));

			// ofertaService.guardarOferta(new Oferta("Camarero", "Hosteleria", "Servir copas", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Cocinero", "Hosteleria", "Preparar alimentos", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Recepcionista", "Hosteleria", "Atender a los clientes", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Mantenimiento", "Hosteleria", "Reparaciones generales", "Jaen", null, 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Animador", "Hosteleria", "Organizar actividades", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Limpieza", "Hosteleria", "Limpieza de habitaciones", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Portero", "Hosteleria", "Vigilar entrada", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Conserje", "Hosteleria", "Atender a los huéspedes", "Jaen", null, 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Mozo de equipaje", "Hosteleria", "Trasladar equipajes", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Jardinero", "Hosteleria", "Cuidado de jardines", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Valet", "Hosteleria", "Servicio de valet", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Sommelier", "Hosteleria", "Recomendar vinos", "Jaen", null, 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Barman", "Hosteleria", "Preparar bebidas", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Guía turístico", "Hosteleria", "Guiar turistas", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Chef", "Hosteleria", "Cocinar platos", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Supervisor", "Hosteleria", "Supervisar personal", "Jaen", null, 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Administrador", "Hosteleria", "Administrar recursos", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Guardia de seguridad", "Hosteleria", "Seguridad del hotel", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Encargado de almacén", "Hosteleria", "Gestionar almacén", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Técnico de mantenimiento", "Hosteleria", "Mantenimiento técnico", "Jaen", null, 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Asistente de eventos", "Hosteleria", "Ayudar en eventos", "Jaen", null, 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Director de hotel", "Hosteleria", "Gestionar el hotel", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Operador de centralita", "Hosteleria", "Atender llamadas", "Jaen", null, 15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));
			ofertaService.guardarOferta(new Oferta("Chef pastelero", "Hosteleria", "Preparar postres", "Jaen", null, 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.TELETRABAJO, LocalDate.of(2008,10,10), contrataService.obtenerTodos().get(0)));

		};
	}

}
