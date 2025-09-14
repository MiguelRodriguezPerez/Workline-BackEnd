package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

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

	// 		contrataService.guardar(new Contrata("Construcciones García", "contacto@cgarcia.com", "Madrid", "600123456", "claveSegura123!"));
	// 		contrataService.guardar(new Contrata("Reformas López", "info@rlopez.es", "Barcelona", "611987654", "passReformas456"));
	// 		contrataService.guardar(new Contrata("Obras Martínez", "obras@martinez.com", "Valencia", "622555444", "construir789"));

	// contrataService.guardar(new Contrata("DIESEL4LIFE", "obras@martinez.com", "Valencia", "622555444", "1234kasdddddddjfA#"));

	// 		buscaService.guardar(new Busca("Juan Pérez", "juan.perez@mail.com", "Madrid", "600111222", "passwordJuan123"));
	// 		buscaService.guardar(new Busca("María López", "maria.lopez@mail.com", "Barcelona", "611333444", "claveMaria456"));
	// 		buscaService.guardar(new Busca("Carlos Sánchez", "carlos.sanchez@mail.com", "Valencia", "622555666", "passCarlos789"));


	// 		// contrataService.guardar(new Contrata("TechCorp", "techcorp@gmail.com", "Madrid", "623456789", "1234kasdddddddjfA#"));
	// 		// contrataService.guardar(new Contrata("InnovateCo", "innovateco@gmail.com", "Barcelona", "624567890", "1234kasdddddddjfA#"));
	// 		// contrataService.guardar(new Contrata("BuildIt", "buildit@gmail.com", "Sevilla", "625678901", "1234kasdddddddjfA#"));
	// 		// contrataService.guardar(new Contrata("GreenTech", "greentech@gmail.com", "Valencia", "626789012", "1234kasdddddddjfA#"));

	// 		// buscaService.guardar(new Busca("roberto","manuel@gmail.com","Cordoba","654344512","1234kasdddddddjfA#"));
	// 		// buscaService.guardar(new Busca("manuel","manuel@gmail.com","Soria","654344512","1234kasdddddddjfA#"));

	// 	};
	// }

}
