package org.sam.lab.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T, I> {

    void setConn(Connection conn);

    List<T> listar() throws SQLException;

    T porId(I id) throws SQLException;

    T guardar(T t) throws SQLException;

    void eliminar(I id) throws SQLException;
}