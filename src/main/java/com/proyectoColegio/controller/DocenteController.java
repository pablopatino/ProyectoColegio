package com.proyectoColegio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.dto.EditarDocenteDTO;
import com.proyectoColegio.dto.IdentificadorDTO;
import com.proyectoColegio.services.docente.ServicesDocente;
import com.proyectoColegio.utils.MensajeRespuesta;

@RestController
@RequestMapping("/docentes")
public class DocenteController {
	
	@Autowired
	ServicesDocente servicesDocente;
	
	public DocenteController(ServicesDocente servicesDocente) {
		this.servicesDocente = servicesDocente;
	}

	@GetMapping("/buscar")
	public ResponseEntity<Usuario> busacarDocente(@RequestBody IdentificadorDTO identificadorDTO){
		return ResponseEntity.status(HttpStatus.OK).body(this.servicesDocente.getDocentePorId(identificadorDTO.getIdentificador()));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<MensajeRespuesta> editarDocente(@RequestBody EditarDocenteDTO editarDocenteDTO){
		return ResponseEntity.status(HttpStatus.OK).body(this.servicesDocente.editarDocente(editarDocenteDTO));
	}
	
	
	@PostMapping("/nuevo")
	public ResponseEntity<Docente> guardarUsuario(@RequestBody Docente docente){
		return ResponseEntity.status(HttpStatus.OK).body(servicesDocente.guardarDocente(docente));
	}
	
}
