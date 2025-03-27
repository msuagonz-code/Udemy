package org.sam.webappear.ear.services;

import jakarta.ejb.Local;
import org.sam.webappear.ear.entities.Usuario;

import java.util.List;

@Local
public interface UsuarioService {
    List<Usuario> listar();
}
