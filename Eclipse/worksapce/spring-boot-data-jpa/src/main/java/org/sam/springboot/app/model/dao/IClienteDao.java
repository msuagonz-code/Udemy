package org.sam.springboot.app.model.dao;

import org.sam.springboot.app.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
