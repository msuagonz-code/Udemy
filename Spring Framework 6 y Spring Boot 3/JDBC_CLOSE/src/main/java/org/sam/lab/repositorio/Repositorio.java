package org.sam.lab.repositorio;

import java.util.List;

public interface Repositorio<T, I> {

    List<T> listar();

    T porId(I id);

    void guardar(T t);

    void eliminar(I id);
}
