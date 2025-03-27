package org.sam.webapp.jsf3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.sam.webapp.jsf3.entities.Categoria;
import org.sam.webapp.jsf3.entities.Producto;
import org.sam.webapp.jsf3.services.ProductoService;

import java.util.List;

@Model
public class ProductoController {


    private Producto producto;

    private Long id;

    @Inject
    private ProductoService productoService;

    @Produces
    @Model
    public String titulo(){
        return "Hola mundo JavaServer Faces 3.0";
    }

    @Produces
    @RequestScoped
    @Named("listado")
    public List<Producto> findAll(){
        //return Arrays.asList(new Producto("pera"), new Producto("manzanas"), new Producto("mandarinas"));
        return productoService.listar();
    }

    @Produces
    @Model
    public Producto producto(){
        this.producto = new Producto();

        if(this.id != null && this.id > 0){
            this.productoService.porId(id).ifPresent( p -> {
                this.producto = p;
            });
        }

        return this.producto;
    }

    @Produces
    @Model
    public List<Categoria> categorias(){
        return productoService.listarCategorias();
    }

    public String guardar(){
        System.out.println(producto);
        productoService.guardar(producto);
        return "index.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id){
        productoService.eliminar(id);
        return "index.xhtml?faces-redirect=true";
    }

    public String editar(Long id){
        this.id = id;
        return "form.xhtml";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}