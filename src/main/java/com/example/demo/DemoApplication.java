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
		System.out.println(
			System.getenv("CLIENT_ALLOWED")
		);
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		SpringApplication.run(DemoApplication.class, args);
	}

}
