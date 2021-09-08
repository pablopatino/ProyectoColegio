package com.proyectoColegio.services;

import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;

public interface ServicesUsuario {
	
	Usuario getUser(String username);
	Usuario guardarUsuario(Usuario usuario);
	Rol guardarRol(Rol rol);
	void agregarRol(String username, String nombreRol);

}
