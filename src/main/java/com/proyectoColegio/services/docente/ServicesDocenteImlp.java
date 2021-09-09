package com.proyectoColegio.services.docente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.dto.EditarDocenteDTO;
import com.proyectoColegio.excepciones.UsuarioRepetido;
import com.proyectoColegio.repositorio.RepositorioDocente;
import com.proyectoColegio.repositorio.RepositorioRol;
import com.proyectoColegio.repositorio.RepositorioUsuario;
import com.proyectoColegio.utils.MensajeRespuesta;

import static com.proyectoColegio.utils.ValidarCampos.*;
import static com.proyectoColegio.utils.VariablesConstantes.*;

@Service
@Transactional
public class ServicesDocenteImlp implements ServicesDocente {
	
	private final RepositorioDocente repositorioDocente;
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	
	@Autowired
	RepositorioRol repositorioRol;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public ServicesDocenteImlp(RepositorioDocente repositorioDocente) {
		this.repositorioDocente = repositorioDocente;
	}

	@Override
	public Docente guardarDocente(Docente docente) {
		
		validarCamposDocente(docente);
		validarIdentificacion(docente.getIdentificacion());
		validarCorreoNoRepetido(docente.getCorreo());
		validarUsernameDocente(docente.getUsername());
		
		docente.setPassword(passwordEncoder.encode(docente.getPassword()));
		docente.getRoles().add(agregarRol());
		
		return this.repositorioDocente.save(docente);
	}
	
	@Override
	public Usuario getDocentePorId(String identificador) {
		validarIdentificacion(identificador);
		return this.repositorioDocente.buscarDocentePorIdentificacion(identificador);
	}
	
	@Override
	public MensajeRespuesta editarDocente(EditarDocenteDTO editarDocenteDTO) {
		validarIdentificacion(editarDocenteDTO.getIdentificador());
		validarCorreoNoRepetido(editarDocenteDTO.getCorreo());
		
		Usuario usuario = this.repositorioUsuario.buscarPorIdentificacion(editarDocenteDTO.getIdentificador());
		usuario.setTelefono(editarDocenteDTO.getTelefono());
		usuario.setCorreo(editarDocenteDTO.getCorreo());
		usuario.setDireccion(editarDocenteDTO.getDireccion());
		
		this.repositorioUsuario.save(usuario);
		
		return new MensajeRespuesta(EDITADOEXITOSO);
	}
	
	private void validarUsernameDocente(String username) {
		Usuario usuario = this.repositorioUsuario.findByUsername(username);
		if (usuario != null) {		
			throw new UsuarioRepetido(USUARIO_REPETIDO);
		}
	}
	
	private void validarCorreoNoRepetido(String correo) {
		Usuario usuario = this.repositorioUsuario.buscarPorCorreo(correo);
		if (usuario != null) {
				throw new UsuarioRepetido(CORREO_REPETIDO);
		}
	}
	
	private void validarIdentificacion(String identificacion) {
		Usuario usuario = this.repositorioUsuario.buscarPorIdentificacion(identificacion);
		if (usuario != null) {
			throw new UsuarioRepetido(IDENTIFICACION_REPETIDA);
		}
	}

	
	private Rol agregarRol() {
		return this.repositorioRol.findByName("ROL_PROFESOR");
	}

}
