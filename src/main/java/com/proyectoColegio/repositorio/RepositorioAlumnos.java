package com.proyectoColegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoColegio.domain.Alumno;

public interface RepositorioAlumnos extends JpaRepository<Alumno, Integer> {

}
