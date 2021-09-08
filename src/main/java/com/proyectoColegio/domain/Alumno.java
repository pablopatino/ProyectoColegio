package com.proyectoColegio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Alumno extends Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Grado grado;

	@ManyToOne
	private Asignatura asignatura;

	public Alumno(Grado grado, Asignatura asignatura) {
		this.grado = grado;
		this.asignatura = asignatura;
	}

	public Alumno(String nombre, String apellido, String identificacion, String correo, String telefono,
			String direccion, String fechaDeNacimiento, String username, String password, Grado grado,
			Asignatura asignatura) {
		super(nombre, apellido, identificacion, correo, telefono, direccion, fechaDeNacimiento, username, password);
		this.grado = grado;
		this.asignatura = asignatura;
	}

	public Alumno() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	private static final long serialVersionUID = 1L;

}
