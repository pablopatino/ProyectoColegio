package com.proyectoColegio.controladorexepciones;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.proyectoColegio.excepciones.UsuarioNoEncontrado;
import com.proyectoColegio.excepciones.UsuarioRepetido;
import com.proyectoColegio.excepciones.ValorRequerido;

@ControllerAdvice
public class ManejadorExcepciones {

private static final ConcurrentHashMap<String, Integer> CODIGO_ESTADO = new ConcurrentHashMap<>();
	
	public ManejadorExcepciones() {
		CODIGO_ESTADO.put(UsuarioNoEncontrado.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
		CODIGO_ESTADO.put(ValorRequerido.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		CODIGO_ESTADO.put(UsuarioRepetido.class.getSimpleName(), HttpStatus.CONFLICT.value());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> allExceptions(Exception exception){
		ResponseEntity<Error> resultado;
		
		String excepcionNombre = exception.getClass().getSimpleName();
		String mensaje = exception.getMessage();
		Integer codigo = CODIGO_ESTADO.get(excepcionNombre);
		
		if (codigo != null) {
            Error error = new Error(excepcionNombre, mensaje);
            resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
		} else {
            Error error = new Error(excepcionNombre, "Internal server Error");
            
            resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
		
		return resultado;
	}
	
}
