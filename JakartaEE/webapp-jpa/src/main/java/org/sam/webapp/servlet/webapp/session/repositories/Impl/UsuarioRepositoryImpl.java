package org.sam.webapp.servlet.webapp.session.repositories.Impl;

import jakarta.inject.Inject;
import org.sam.webapp.servlet.webapp.session.configs.OracleConn;
import org.sam.webapp.servlet.webapp.session.configs.Repository;
import org.sam.webapp.servlet.webapp.session.models.entities.Usuario;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;
import org.sam.webapp.servlet.webapp.session.repositories.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RepositoryJpa
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    @OracleConn
    private Connection conn;

    private static Usuario getUsuarioFromResulset(ResultSet resultSet) throws SQLException {
        Usuario usuario;
        usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setUsername(resultSet.getString("username"));
        usuario.setPassword(resultSet.getString("password"));
        usuario.setEmail(resultSet.getString("email"));
        return usuario;
    }

    @Override
    public Usuario getByUsername(String username) throws SQLException {
        Usuario usuario = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username = ?")){
            stmt.setString(1, username);

            try(ResultSet resultSet = stmt.executeQuery()){
                if(resultSet.next()){
                    usuario = getUsuarioFromResulset(resultSet);
                }
            }
        }
        return usuario;
    }


    @Override
    public List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM usuarios ORDER BY ID ASC")){
            while (resultSet.next()){
                Usuario usuarioFromResulset = getUsuarioFromResulset(resultSet);
                usuarios.add(usuarioFromResulset);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario getById(Long id) throws SQLException {
        Usuario usuario = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?")){
            stmt.setLong(1, id);
            try(ResultSet resultSet = stmt.executeQuery()){
                if(resultSet.next()){
                    usuario = getUsuarioFromResulset(resultSet);
                }
            }
        }
        return usuario;
    }

    public Usuario getLastId() throws SQLException {
        Usuario usuario = null;
        // Obtenemos el ultimo registro
        try(PreparedStatement statement = conn.prepareStatement("SELECT ID " +
                " FROM ( " +
                "    SELECT ID " +
                "    FROM USUARIOS " +
                "    ORDER BY ID DESC " +
                " ) " +
                " WHERE ROWNUM = 1")){

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    usuario = new Usuario();
                    usuario.setId(resultSet.getLong("ID"));
                }
            }
        }
        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String sql;
        Usuario lastUsuario = null;

        if(usuario.getId() != null && usuario.getId() > 0){
            //Update
            sql = "UPDATE USUARIOS SET username = ?, password = ?, email = ? WHERE id = ?";
        }else{
            //Insert
            lastUsuario = getLastId();
            sql = "INSERT INTO USUARIOS (username, password, email, id) VALUES (?,?,?,?)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            if(lastUsuario != null){
                stmt.setLong(4, (lastUsuario.getId() + 1));
            }else {
                stmt.setLong(4, usuario.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE ID = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
