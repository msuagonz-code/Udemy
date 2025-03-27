package org.sam.webapp.servlet.webapp.session.repositories.Impl;

import jakarta.inject.Inject;
import org.sam.webapp.servlet.webapp.session.configs.OracleConn;
import org.sam.webapp.servlet.webapp.session.configs.Repository;
import org.sam.webapp.servlet.webapp.session.models.entities.Categoria;
import org.sam.webapp.servlet.webapp.session.repositories.CrudRepository;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RepositoryJpa
public class CategoriaRepositorioImpl implements CrudRepository<Categoria> {

    private Connection conn;

     @Inject
    public CategoriaRepositorioImpl(@OracleConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> getAll() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORY")){
            while (rs.next()){
                Categoria categoria = getCategoriaFromResulset(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria getById(Long id) throws SQLException {
        Categoria categoria = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CATEGORY WHERE ID = ?")){
            stmt.setLong(1, id);
            try(ResultSet resultSet = stmt.executeQuery()){
                if(resultSet.next()){
                    categoria = getCategoriaFromResulset(resultSet);
                }
            }
        }

        return categoria;
    }

    @Override
    public void update(Categoria categoria) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    public Categoria getCategoriaFromResulset(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("ID"));
        categoria.setName(resultSet.getString("NAME"));
        return categoria;
    }
}
