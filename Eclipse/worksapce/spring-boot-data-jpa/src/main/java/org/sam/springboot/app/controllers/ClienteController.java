package org.sam.springboot.app.controllers;

import java.util.Map;

import org.sam.springboot.app.model.dao.IClienteDao;
import org.sam.springboot.app.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;
	
	@RequestMapping(value="listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("titulo", "Formulario de Cliente");
		model.put("cliente", cliente);
		
		return "form";
	}
	
}
