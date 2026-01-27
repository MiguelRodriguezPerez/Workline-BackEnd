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

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		/* NOTA: Extrañamente System.getenv("WHATEVER") funciona en prod, pero no en dev
		Necesitas Dotenv */
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(DemoApplication.class, args);
	}

}
