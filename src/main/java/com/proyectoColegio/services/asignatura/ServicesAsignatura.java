package com.proyectoColegio.services.asignatura;

import com.proyectoColegio.domain.Asignatura;

public interface ServicesAsignatura {

	Asignatura crearAsignatura(Asignatura asignatura);
	void agregarProfesorAlCurso(String idProfesor, String nombreAsignatura );
	
}
