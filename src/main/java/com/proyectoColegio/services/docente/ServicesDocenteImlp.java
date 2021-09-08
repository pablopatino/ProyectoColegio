package com.proyectoColegio.services.docente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.repositorio.RepositorioDocente;

@Service
@Transactional
public class ServicesDocenteImlp implements ServicesDocente {

	private final RepositorioDocente repositorioDocente;

	@Autowired
	public ServicesDocenteImlp(RepositorioDocente repositorioDocente) {
		this.repositorioDocente = repositorioDocente;
	}

	@Override
	public Docente guardarDocente(Docente docente) {
		return this.repositorioDocente.save(docente);
	}

}
