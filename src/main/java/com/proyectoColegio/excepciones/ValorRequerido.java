package com.proyectoColegio.excepciones;

public class ValorRequerido extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public ValorRequerido(String message) {
		super(message);
	}
}
