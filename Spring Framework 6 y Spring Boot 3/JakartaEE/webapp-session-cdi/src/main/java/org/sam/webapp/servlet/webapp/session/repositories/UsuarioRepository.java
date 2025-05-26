package org.sam.webapp.servlet.webapp.session.repositories;

import org.sam.webapp.servlet.webapp.session.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario getByUsername(String username)  throws SQLException;

}