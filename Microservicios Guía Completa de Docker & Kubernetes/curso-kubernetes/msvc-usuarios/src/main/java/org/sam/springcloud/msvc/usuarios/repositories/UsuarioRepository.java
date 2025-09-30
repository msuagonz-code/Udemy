package org.sam.springcloud.msvc.usuarios.repositories;

import org.sam.springcloud.msvc.usuarios.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
