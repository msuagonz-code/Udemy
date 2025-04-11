package org.sam.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/params")
public class EjemploParamsControllers {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo", "Enviar parámetros del Request HTTP GET - URL");
		return "params/index";
	}
	
	@GetMapping("/string")
	public String param(@RequestParam(name="texto", required =false, defaultValue = "") String texto, Model model) {
		model.addAttribute("titulo", "Recibir parámetros del Request HTTP GET - URL");
		model.addAttribute("resultado", "El texto enviado es : " + texto);
		return "params/ver";
	}
	
	@GetMapping("/mix-params")
	public String param(@RequestParam String saludo, @RequestParam Integer numero , Model model) {
		model.addAttribute("titulo", "Recibir parámetros del Request HTTP GET - URL");
		model.addAttribute("resultado", "El saludo enviado es : '" + saludo +"' y El numero es : '"+ numero +"'");
		return "params/ver";
	}
	
	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request , Model model) {
		
		String saludo = request.getParameter("saludo");
		Integer numero = null;
		
		try {
			numero = Integer.valueOf(request.getParameter("numero"));
		} catch (NumberFormatException e) {
			numero = 0;
		}
		
		model.addAttribute("titulo", "Recibir parámetros del Request HTTP GET - URL");
		model.addAttribute("resultado", "El saludo enviado es : '" + saludo +"' y El numero es : '"+ numero +"'");
		return "params/ver";
	}
}
