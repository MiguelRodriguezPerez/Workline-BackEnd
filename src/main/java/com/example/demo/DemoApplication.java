package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.busca.conocimiento.Conocimiento;
import com.example.demo.domain.usuarios.busca.experiencia.Experiencia;
import com.example.demo.domain.usuarios.contrata.Contrata;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.admin.AdminService;
import com.example.demo.services.usuarios.busca.BuscaService;
import com.example.demo.services.usuarios.conocimiento.ConocimientoService;
import com.example.demo.services.usuarios.contrata.ContrataService;
import com.example.demo.services.usuarios.experiencia.ExperienciaService;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(title = "Workline Api", version = "1.0.0"),
	servers = {
		@Server(url = "localhost:9001"),
		@Server(url = "http://www.worklinejobs.com/")
	}
)
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

	// 	};
	// }

}
