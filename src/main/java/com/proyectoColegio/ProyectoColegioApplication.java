package com.proyectoColegio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.services.ServicesUsuario;

@SpringBootApplication
public class ProyectoColegioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoColegioApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ServicesUsuario servicesUsuario) {
		return args -> {
			
			servicesUsuario.guardarRol(new Rol("ROL_ADMIN"));
			servicesUsuario.guardarRol(new Rol("ROL_USER"));
			servicesUsuario.guardarRol(new Rol("ROL_PROFESOR"));
			
			servicesUsuario.guardarUsuario(new Usuario("Andmin", "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin"));
			servicesUsuario.agregarRol("admin", "ROL_ADMIN");
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
