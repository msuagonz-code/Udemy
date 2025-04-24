package org.sam.springboot.form.app.services;

import java.util.List;

import org.sam.springboot.form.app.models.domain.Role;

public interface RoleService {

	public List<Role> listar();
	public Role obtenerPorId(Integer id);
	
}
