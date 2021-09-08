package com.proyectoColegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoColegio.domain.Grado;

public interface RepositorioGrado extends JpaRepository<Grado, Integer> {

}
