package com.proyectoColegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoColegio.domain.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
	
}
