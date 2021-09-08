package com.proyectoColegio;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.proyectoColegio.domain.Alumno;
import com.proyectoColegio.domain.Asignatura;
import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Grado;
import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.services.ServicesUsuario;
import com.proyectoColegio.services.alumnos.ServicesAlumnos;
import com.proyectoColegio.services.asignatura.ServicesAsignatura;
import com.proyectoColegio.services.docente.ServicesDocente;
import com.proyectoColegio.services.grado.ServicesGrado;

@SpringBootApplication
public class ProyectoColegioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoColegioApplication.class, args);
	}

}
