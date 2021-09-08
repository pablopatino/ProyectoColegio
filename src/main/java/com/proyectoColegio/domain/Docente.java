package com.proyectoColegio.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Docente extends Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String especialidad;

	@OneToOne(optional = true)
	private Asignatura asignatura;

	
	public Docente(String nombre, String apellido, String identificacion, String correo, String telefono,
			String direccion, String fechaDeNacimiento, String username, String password, String especialidad,
			Asignatura asignatura) {
		super(nombre, apellido, identificacion, correo, telefono, direccion, fechaDeNacimiento, username, password);
		this.especialidad = especialidad;
		this.asignatura = asignatura;
	}


	public Docente() {
	}
	
	
	public Docente(String especialidad, Asignatura asignatura) {
		this.especialidad = especialidad;
		this.asignatura = asignatura;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	private static final long serialVersionUID = 1L;
}
