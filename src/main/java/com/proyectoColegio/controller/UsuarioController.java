package com.proyectoColegio.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.security.jwt.JwtProvider;
import com.proyectoColegio.services.ServicesUsuario;
import com.proyectoColegio.services.docente.ServicesDocente;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

	
	@Autowired
	ServicesUsuario servicesUsuario;
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	ServicesDocente servicesDocente;
	
	@PostMapping("/nuevo/usuario")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(servicesUsuario.guardarUsuario(usuario));
	}
	
	@PostMapping("/nuevo/rol")
	public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol){
		return ResponseEntity.status(HttpStatus.OK).body(servicesUsuario.guardarRol(rol));
	}
	
	@GetMapping("/refresh/token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		jwtProvider.refrescarTokens(request, response);
	}
	
}
