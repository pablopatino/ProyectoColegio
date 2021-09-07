package com.proyectoColegio.excepciones;

public class UsuarioNoEncontrado extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontrado(String message) {
		super(message);
	}

}
