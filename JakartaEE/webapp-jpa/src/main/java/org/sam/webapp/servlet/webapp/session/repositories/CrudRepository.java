package org.sam.webapp.servlet.webapp.session.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> getAll() throws Exception;
    T getById(Long id) throws Exception;
    void update(T t) throws Exception;
    void delete(Long id) throws Exception;
}
