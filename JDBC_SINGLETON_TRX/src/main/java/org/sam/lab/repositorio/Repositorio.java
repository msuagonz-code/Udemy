package org.sam.lab.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T, I> {

    List<T> listar() throws SQLException;

    T porId(I id) throws SQLException;

    void guardar(T t) throws SQLException;

    void eliminar(I id) throws SQLException;
}
