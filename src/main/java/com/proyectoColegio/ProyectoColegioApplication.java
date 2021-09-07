package com.proyectoColegio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.services.ServicesUsuario;

@SpringBootApplication
public class ProyectoColegioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoColegioApplication.class, args);
	}
	

	@Bean
	CommandLineRunner run(ServicesUsuario servicesUsuario){
		return args -> {
			
			servicesUsuario.guardarUsuario(new Usuario("Pablo", "Patino", "1234", "pablo@correo", "333244", "caeer3", "14/05/05", "ppatino", "1234"));
			servicesUsuario.guardarRol(new Rol("ROL_ADMIN"));
			servicesUsuario.agregarRol("ppatino", "ROL_ADMIN");
			
		};
	}

}
