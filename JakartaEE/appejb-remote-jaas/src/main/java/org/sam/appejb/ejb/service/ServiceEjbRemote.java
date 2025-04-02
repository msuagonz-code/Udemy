package org.sam.appejb.ejb.service;

import jakarta.ejb.Remote;
import org.sam.appejb.ejb.models.Producto;

import java.util.List;

@Remote
public interface ServiceEjbRemote {
    String saludar(String nombre);
    List<Producto> listar();
    Producto crear(Producto producto);
}
