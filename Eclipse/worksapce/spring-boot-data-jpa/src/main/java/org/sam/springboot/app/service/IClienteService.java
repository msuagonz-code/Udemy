package org.sam.springboot.app.service;

import java.util.List;

import org.sam.springboot.app.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findOne(Long id);

	public void save(Cliente cliente);
	
	public void delete(Long id);
	
}
