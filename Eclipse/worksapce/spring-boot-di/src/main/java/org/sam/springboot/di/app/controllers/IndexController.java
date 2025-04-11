package org.sam.springboot.di.app.controllers;

import org.sam.springboot.di.app.models.service.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	// Sin inyeccion de dependencia, altamente acoplado
	// private MiServicio servicio = new MiServicio();

	// Principio Hollywood - No nos llames, nosotros te llamaremos, sigue estando
	// acoplado
	// porque depende de una clase concreta MiServicio
	// @Autowired
	// private MiServicio servicio;

	// Inyeccion de dependencia esto es lo ideal
	// @Autowired
	// private IServicio servicio;

	// Inyeccion de dependencia con calificador
	@Autowired
	@Qualifier("miServicioComplejo")
	private IServicio servicio;

	/*
	 * // Inyeccion por constructor 
	 * // no es necesario poner el @Autowired si se
	 * usar el constructor public IndexController(IServicio servicio) {
	 * this.servicio = servicio; }
	 */

	@GetMapping({ "", "/", "/index" })
	public String index(Model model) {

		model.addAttribute("titulo", "Sin inyección de dependencia");
		model.addAttribute("objeto", servicio.operacion());

		return "index";
	}

	// Inyeccion por setter
	/*
	 * @Autowired public void setServicio(MiServicio servicio) { this.servicio =
	 * servicio; }
	 */

}
