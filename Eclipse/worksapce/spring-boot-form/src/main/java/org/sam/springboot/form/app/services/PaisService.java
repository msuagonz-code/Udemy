package org.sam.springboot.form.app.services;

import java.util.List;

import org.sam.springboot.form.app.models.domain.Pais;

public interface PaisService {

	public List<Pais> listar();
	public Pais obtenerPorId(Integer Id);
	
}
