package org.sam.webapp.servlet.webapp.session.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
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

}
