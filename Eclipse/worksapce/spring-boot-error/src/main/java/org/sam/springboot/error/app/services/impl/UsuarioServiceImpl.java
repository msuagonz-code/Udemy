package org.sam.springboot.error.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sam.springboot.error.app.models.domain.Usuario;
import org.sam.springboot.error.app.services.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;
	
	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1, "Manuel", "Suarez"));
		this.lista.add(new Usuario(2, "Stefany", "Vaca"));
		this.lista.add(new Usuario(3, "Andres", "Guzmán"));
		this.lista.add(new Usuario(4, "Pepa", "García"));
		this.lista.add(new Usuario(5, "Lalo", "Mena"));
		this.lista.add(new Usuario(6, "Yuli", "Vaca"));
		this.lista.add(new Usuario(7, "Dalia", "González"));
		this.lista.add(new Usuario(8, "Juan", "Padilla"));
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		
		for(Usuario u : this.lista) {
			if(u.getId().equals(id)) {
				resultado = u;
				break;
			}
		}
		
		return resultado;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		Usuario usuario = this.obtenerPorId(id);
		return Optional.ofNullable(usuario);
	}

}
