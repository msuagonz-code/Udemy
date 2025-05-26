package org.sam.repositories.impl;

import org.sam.db.ConnectionJDBC;
import org.sam.models.Product;
import org.sam.repositories.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();

        try(Connection conn = ConnectionJDBC.getConnecion();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM PRODUCTS")){

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getInt("PRICE"),
                        resultSet.getInt("QUANTITY")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;

        try(Connection conn = ConnectionJDBC.getConnecion();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCTS WHERE ID = ?");
            ){
            stmt.setLong(1, id);

            try(ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next()) {
                    product = new Product(resultSet.getLong("ID"),
                            resultSet.getString("NAME"),
                            resultSet.getInt("PRICE"),
                            resultSet.getInt("QUANTITY")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product save(Product product) {

        String sql = "";
        boolean isInsert = true;
        Product existProduct = findById(product.getId());
        if(existProduct != null){//Update
            sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
            isInsert = false;
        }else{// Insert
            sql = "INSERT INTO products (id, name, price, quantity) VALUES(?, ?, ?, ?)";
        }

        try(Connection conn = ConnectionJDBC.getConnecion();
        PreparedStatement stmt = conn.prepareStatement(sql) ){

            if(isInsert){
                stmt.setLong(1, product.getId());
                stmt.setString(2, product.getName());
                stmt.setInt(3, product.getPrice());
                stmt.setInt(4, product.getQuantity());
            }else{
                stmt.setString(1, product.getName());
                stmt.setInt(2, product.getPrice());
                stmt.setInt(3, product.getQuantity());
                stmt.setLong(4, product.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void delete(Long id) {
        try(Connection conn = ConnectionJDBC.getConnecion();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM PRODUCTS WHERE ID = ?"))
        {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
