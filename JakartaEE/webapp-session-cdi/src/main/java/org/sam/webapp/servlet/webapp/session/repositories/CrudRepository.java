package org.sam.webapp.servlet.webapp.session.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> getAll() throws SQLException;
    T getById(Long id) throws SQLException;
    void update(T t) throws SQLException;
    void delete(Long id) throws SQLException;
}
