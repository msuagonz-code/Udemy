package org.sam.webapp.servlet.webapp.session.repositories.Impl;

import org.sam.webapp.servlet.webapp.session.models.Categoria;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {

    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> getAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT " +
                    "    P.ID, " +
                    "    P.NAME, " +
                    "    P.PRICE, " +
                    "    P.SKU, " +
                    "    P.DATE_REGISTRY, " +
                    "    C.ID AS ID_TIPO, " +
                    "    C.NAME AS TIPO " +
                    " FROM PRODUCTS P JOIN CATEGORY C ON C.ID = P.CATEGORY_ID ")){

            while(resultSet.next()){
                Producto producto = getProductoFromResulset(resultSet);
                productos.add(producto);
            }

        }

        return productos;
    }

    @Override
    public Producto getById(Long id) throws SQLException {
        Producto producto = null;

        try(PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT" +
                "    P.ID, " +
                "    P.NAME, " +
                "    P.PRICE, " +
                "    P.SKU, " +
                "    P.DATE_REGISTRY, " +
                "    C.ID AS ID_TIPO, " +
                "    C.NAME AS TIPO " +
                " FROM PRODUCTS P JOIN CATEGORY C ON C.ID = P.CATEGORY_ID WHERE P.ID = ?")){

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    producto = getProductoFromResulset(resultSet);
                }
            }

        }

        return producto;
    }

    public Producto getLastId() throws SQLException {
        Producto producto = null;
        // Obtenemos el ultimo registro
        try(PreparedStatement statement = conn.prepareStatement("SELECT * " +
                " FROM ( " +
                "    SELECT * " +
                "    FROM PRODUCTS " +
                "    ORDER BY id DESC " +
                " ) " +
                " WHERE ROWNUM = 1")){

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    producto = getProductoFromResulset(resultSet);
                }
            }
        }

        return producto;
    }

    @Override
    public void update(Producto producto) throws SQLException {

        Producto lastProduct = null;
        String sql;
        if(producto.getId() != null && producto.getId() > 0){
            sql = "UPDATE PRODUCTS NAME = ?, PRICE = ?, SKU = ?, CATEGORY_ID = ?  SET WHERE ID = ?";
        }else{
            lastProduct = getLastId();
            sql = "INSER INTO PRODUCTS (NAME, PRICE, SKU, CATEGORY_ID, ID, DATE_REGISTRY) VALUES (?,?,?,?,?,?)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,  producto.getNombre());
            stmt.setLong(2,  producto.getPrecio());
            stmt.setString(3, producto.getSku()  );
            stmt.setLong (4,  producto.getCategoria().getId());
            stmt.setLong(5, Objects.requireNonNullElse(lastProduct, producto).getId());

            /*
             * Esta sentencia es equivalente a esto:
             *
             *   if(lastProduct == null) {
             *       stmt.setLong(5, producto.getId());
             *   }else{
             *       stmt.setLong(5, lastProduct.getId());
             *   }
             *
            */
            if(lastProduct == null) {
                stmt.setDate(6, Date.valueOf(producto.getFechaRegistro()));
            }

            stmt.executeUpdate();
        }

    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM PRODUCTS WHERE ID = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Producto getProductoFromResulset(ResultSet resultSet) throws SQLException {

        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("ID_TIPO"));
        categoria.setName(resultSet.getString("TIPO"));

        Producto producto = new Producto();
        producto.setId(resultSet.getLong("ID"));
        producto.setNombre(resultSet.getString("NAME"));
        producto.setPrecio(resultSet.getInt("PRICE"));
        producto.setSku(resultSet.getString("SKU"));

        Timestamp timestamp = resultSet.getTimestamp("DATE_REGISTRY");
        if (timestamp != null) {
            producto.setFechaRegistro(timestamp.toLocalDateTime().toLocalDate());
        } else {
            // Manejo de null si es necesario
            producto.setFechaRegistro(null);
        }

        producto.setCategoria(categoria);

        return producto;
    }
}
