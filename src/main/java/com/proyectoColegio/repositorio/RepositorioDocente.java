package com.proyectoColegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Usuario;

public interface RepositorioDocente extends JpaRepository<Docente,Integer> {
	
	
	@Query(value = "SELECT * FROM seguridad.usuario as A inner join seguridad.docente as B ON A.id = B.id WHERE A.identificacion = :idDocente", nativeQuery = true)
	public Docente buscarDocentePorIdentificacion(String idDocente);

}
