package org.sam.webapp.servlet.webapp.session.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.sam.webapp.servlet.webapp.session.configs.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CarroCompra
public class Carro implements Serializable {

    // Siempre que se usea la anotación @SessionScoped
    // se debe declarar la clase con implements Serializable

    @Inject
    private transient Logger log;

    private List<ItemCarro> items;

    @PostConstruct
    public void inicializar(){
        this.items = new ArrayList<>();
        log.info("inicializando Carro de compra");
    }

    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el carro de compras");
    }

    public void addItem(ItemCarro itemCarro){
        if(this.items.contains(itemCarro)){

            Optional<ItemCarro> optionalItemCarro = this.items.stream()
                    .filter(item -> item.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro item = optionalItemCarro.get();
                item.setCantidad(item.getCantidad()+1);
            }
        }else{
            this.items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal(){
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
            // que es lo mismo a:
            // productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
