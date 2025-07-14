package org.sam.appejb.ejb.service;

import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import org.sam.appejb.ejb.models.Producto;

import java.util.ArrayList;
import java.util.List;

@Stateful
public class ServiceEjb implements ServiceEjbRemote {

    private int contador;

    public String saludar(String nombre){
        System.out.println("Imprimiendo dentro del ejb con instancia: "+ this);
        contador++;
        System.out.println("Valor del contador en el metodo saludar: "+ contador);
        return "Hola que tal "+ nombre;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Peras"));
        productos.add(new Producto("Manzanas"));
        productos.add(new Producto("Naranjas"));
        return productos;
    }

    @Override
    public Producto crear(Producto producto) {
        System.out.println("Guardando producto... "+ producto.getNombre());
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre(producto.getNombre());
        return productoNuevo;
    }
}
