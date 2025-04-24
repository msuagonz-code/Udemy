package org.sam.springboot.error.app.errors;

public class UsuarioNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoException(String id) {
		super("usuario con ID: ".concat(id).concat(" no existe en el sistema"));
		// TODO Auto-generated constructor stub
	}

	
	
}
