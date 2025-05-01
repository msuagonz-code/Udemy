package org.sam.springboot.app.model.dao.impl;

import java.util.List;

import org.sam.springboot.app.model.dao.IClienteDao;
import org.sam.springboot.app.model.entity.Cliente;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
	
	@Override
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		}else {			
			em.persist(cliente);
		}
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
