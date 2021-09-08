package com.proyectoColegio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Asignatura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombreAsignatura;

	@OneToOne
	private Docente docente;

	@OneToMany
	private List<Alumno> alumnos = new ArrayList<>();

	@ManyToOne
	private Grado grado;

	public Asignatura(String nombreAsignatura, Docente docente, Grado grado) {
		this.nombreAsignatura = nombreAsignatura;
		this.docente = docente;
		this.grado = grado;
	}

	public Asignatura() {
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	private static final long serialVersionUID = 1L;

}
