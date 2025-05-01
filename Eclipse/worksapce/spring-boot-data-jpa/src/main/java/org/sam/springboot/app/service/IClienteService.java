package org.sam.springboot.app.service;

import java.util.List;

import org.sam.springboot.app.model.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente findOne(Long id);

	public void save(Cliente cliente);
	
	public void delete(Long id);
	
}
