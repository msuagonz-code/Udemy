package org.sam.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sam.springboot.form.app.editors.NombreMayusculaEditor;
import org.sam.springboot.form.app.editors.PaisPropertyEditor;
import org.sam.springboot.form.app.editors.RolesEditor;
import org.sam.springboot.form.app.models.domain.Pais;
import org.sam.springboot.form.app.models.domain.Role;
import org.sam.springboot.form.app.models.domain.Usuario;
import org.sam.springboot.form.app.services.PaisService;
import org.sam.springboot.form.app.services.RoleService;
import org.sam.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class FormController {

	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Con set, quita las demas validaciones y deja solo validador
		//binder.setValidator(validador);
		
		//Con addValidators agrega nuestro validador sin eliminar los otros validadores
		binder.addValidators(validador);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		
		//new CustomDateEditor(dateFormat, true) activa el mensaje @NotNull en Usuario porque permite null y se pasa al modelo
		
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setIdentificador("13.456.789-K");
		usuario.setNombre("John");
		usuario.setApellido("wick");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Parangaracutirimicuaro");
		usuario.setPais(new Pais( 7, "VE", "Venezuela"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
		
		model.addAttribute("titulo", "Formulario Usuarios");
		model.addAttribute("user", usuario);
		return "form";
	} 
		
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model) {
		
		//validador.validate(usuario, result);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Resultado Form");
			return "form";
		}

	return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="user", required = false) Usuario usuario, Model model, SessionStatus status) {
		
		if(usuario== null) {
			return "redirect:/form";
		}
		
		model.addAttribute("titulo", "Resultado Form");
		model.addAttribute("usuario", usuario);
		
		status.setComplete();
		return "resultado";
	}
	
	
	@ModelAttribute("listaGenero")
	public List<String> genero(){
		return Arrays.asList("Hombre", "Mujer", "No binario");
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return this.paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap(){
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		return roles;
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("MX", "Mexico");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Perú");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		return paises;
	}
	
	/*
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model) {
		
		model.addAttribute("titulo", "Resultado Form");
		
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "form";
		}
		
		model.addAttribute("usuario", usuario);
		
	return "resultado";
	}
	 */
	
	/*
	@PostMapping("/form")
	public String procesar(Model model
			, @RequestParam(name ="username") String username
			, @RequestParam(value ="password") String password
			, @RequestParam String email) {
		
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);
		
		model.addAttribute("titulo", "Resultado Form");
		model.addAttribute("usuario", usuario);
		
	return "resultado";
	}
	*/
}
