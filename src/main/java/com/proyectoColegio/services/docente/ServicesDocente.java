package com.proyectoColegio.services.docente;

import com.proyectoColegio.domain.Docente;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.dto.EditarDocenteDTO;
import com.proyectoColegio.utils.MensajeRespuesta;

public interface ServicesDocente {

	Docente guardarDocente(Docente docente);
	Usuario getDocentePorId(String identificador);
	MensajeRespuesta editarDocente(EditarDocenteDTO editarDocenteDTO);
	
}
