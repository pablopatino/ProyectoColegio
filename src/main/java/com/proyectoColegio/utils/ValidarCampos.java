package com.proyectoColegio.utils;

import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.excepciones.ValorRequerido;

import static com.proyectoColegio.utils.VariablesConstantes.*;

public class ValidarCampos {
	
	private ValidarCampos() {
		 throw new IllegalStateException("Utility class");
	}
	
	//TODO VALIDACIONES DE ESPACIOS....
	
	public static void validarCamposStrign(Object string) {
		if ( string==null || string.toString().isEmpty()) {
			erroValorRequerido();
		}
	}
	
	public static void validarUsuario(Usuario usuario) {
		validarCamposStrign(usuario.getNombre());
		validarCamposStrign(usuario.getApellido());
		validarCamposStrign(usuario.getIdentificacion());
		validarCamposStrign(usuario.getCorreo());
		validarCamposStrign(usuario.getTelefono());
		validarCamposStrign(usuario.getDireccion());
		validarCamposStrign(usuario.getFechaDeNacimiento());
		validarCamposStrign(usuario.getUsername());
		validarCamposStrign(usuario.getPassword());
		
	}
	
	public static void validarCamposDocente(Docente docente) {
		validarCamposStrign(docente.getNombre());
		validarCamposStrign(docente.getApellido());
		validarCamposStrign(docente.getIdentificacion());
		validarCamposStrign(docente.getCorreo());
		validarCamposStrign(docente.getTelefono());
		validarCamposStrign(docente.getDireccion());
		validarCamposStrign(docente.getFechaDeNacimiento());
		validarCamposStrign(docente.getUsername());
		validarCamposStrign(docente.getPassword());
		validarCamposStrign(docente.getEspecialidad());
	}
		
	private static ValorRequerido erroValorRequerido() {
		throw new ValorRequerido(VALOR_REQUERIDO);
	}
	

}
