package com.proyectoColegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoColegio.domain.Rol;

public interface RepositorioRol extends JpaRepository<Rol, Integer>{

	Rol findByName(String nombreRol);
	
}
