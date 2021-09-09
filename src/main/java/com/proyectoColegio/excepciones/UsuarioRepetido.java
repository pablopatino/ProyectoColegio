package com.proyectoColegio.excepciones;

public class UsuarioRepetido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioRepetido(String message) {
		super(message);
	}

	
	
}
