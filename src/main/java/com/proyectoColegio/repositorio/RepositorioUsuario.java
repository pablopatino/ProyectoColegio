package com.proyectoColegio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectoColegio.domain.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
	
	@Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
	Usuario buscarPorCorreo(String correo);
	
	@Query("SELECT u FROM Usuario u WHERE u.identificacion = :identificacion")
	Usuario buscarPorIdentificacion(String identificacion);

}
