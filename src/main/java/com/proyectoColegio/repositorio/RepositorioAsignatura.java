package com.proyectoColegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectoColegio.domain.Asignatura;

public interface RepositorioAsignatura extends JpaRepository<Asignatura, Integer> {

	@Query(value = "SELECT * FROM seguridad.asignatura WHERE nombre_asignatura = :nombreAsignatura", nativeQuery = true)
	public Asignatura buscarNombrePorAsignatura(String nombreAsignatura);
	
}
