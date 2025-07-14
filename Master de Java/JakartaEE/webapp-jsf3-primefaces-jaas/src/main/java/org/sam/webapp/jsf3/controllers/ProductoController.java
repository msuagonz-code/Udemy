package org.sam.webapp.jsf3.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.sam.webapp.jsf3.config.CustomFacesContext;
import org.sam.webapp.jsf3.entities.Categoria;
import org.sam.webapp.jsf3.entities.Producto;
import org.sam.webapp.jsf3.services.ProductoService;

import java.util.List;
import java.util.ResourceBundle;

@Model
public class ProductoController {

    private Long id;

    private Producto producto;

    @Inject
    private ProductoService productoService;

    @Inject
    @CustomFacesContext
    private FacesContext facesContext;

    @Inject
    private ResourceBundle bundle;

    private List<Producto> listado;

    private String textoBuscar;

    @PostConstruct
    public void init(){
        this.listado = productoService.listar();
    }

    @Produces
    @Model
    public String titulo(){
        //return "Hola mundo JavaServer Faces 3.0";
        return bundle.getString("producto.texto.titulo");
    }

    /*
    @Produces
    @RequestScoped
    @Named("listado")
    public List<Producto> findAll(){
        return productoService.listar();
    }*/

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
        if(producto.getId() != null && producto.getId() > 0){
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.editar"), producto.getNombre())));
        }else{
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.crear"), producto.getNombre())));
        }
        productoService.guardar(producto);
        listado = productoService.listar();
        return "index.xhtml";
    }

    public void eliminar(Producto producto){
        productoService.eliminar(producto.getId());
        facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.eliminar"), producto.getNombre())));
        listado = productoService.listar();
    }

    public void buscar(){
        listado = productoService.buscarPorNombre(this.textoBuscar);
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

    public List<Producto> getListado() {
        return listado;
    }

    public void setListado(List<Producto> listado) {
        this.listado = listado;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }
}