package com.proyectoColegio.services.alumnos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoColegio.domain.Alumno;
import com.proyectoColegio.repositorio.RepositorioAlumnos;


@Service
@Transactional
public class ServicesAlumnoImlp implements ServicesAlumnos {
	
	private final RepositorioAlumnos repositorioAlumnos;

	@Autowired
	public ServicesAlumnoImlp(RepositorioAlumnos repositorioAlumnos) {
		this.repositorioAlumnos = repositorioAlumnos;
	}

	@Override
	public Alumno guardarAlumno(Alumno alumno) {
		return this.repositorioAlumnos.save(alumno);
	}

}
