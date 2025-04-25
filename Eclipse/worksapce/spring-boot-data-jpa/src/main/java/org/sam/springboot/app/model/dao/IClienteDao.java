package org.sam.springboot.app.model.dao;

import java.util.List;

import org.sam.springboot.app.model.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
}
