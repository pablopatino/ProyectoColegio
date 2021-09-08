package com.proyectoColegio.services.grado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoColegio.domain.Grado;
import com.proyectoColegio.repositorio.RepositorioGrado;


@Service
@Transactional
public class ServicesGradoImlp implements ServicesGrado {
	
	@Autowired
	RepositorioGrado repositorioGrado;

	@Override
	public Grado guardarGrado(Grado grado) {
		return this.repositorioGrado.save(grado);
	}

}
