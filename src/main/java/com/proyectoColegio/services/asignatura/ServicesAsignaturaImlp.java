package com.proyectoColegio.services.asignatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoColegio.domain.Asignatura;
import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.repositorio.RepositorioAsignatura;
import com.proyectoColegio.repositorio.RepositorioDocente;


@Service
@Transactional
public class ServicesAsignaturaImlp implements ServicesAsignatura {

	@Autowired
	RepositorioAsignatura repositorioAsignatura;
	@Autowired
	RepositorioDocente repositorioDocente;
	
	@Override
	public Asignatura crearAsignatura(Asignatura asignatura) {
			return this.repositorioAsignatura.save(asignatura);
	}
	
	@Override
	public void agregarProfesorAlCurso(String idProfesor, String nombreAsignatura) {
		Docente docente = this.repositorioDocente.buscarDocentePorIdentificacion(idProfesor);
		Asignatura asignatura = this.repositorioAsignatura.buscarNombrePorAsignatura(nombreAsignatura);
		docente.setAsignatura(asignatura);
	
	}

}
