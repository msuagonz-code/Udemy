package org.sam.springboot.di.app.springboot_di.models;

public class Product implements Cloneable{

    private Long id;
    private String Name;
    private Long price;

    public Product() {
    }

    public Product(Long id, String name, Long price) {
        this.id = id;
        Name = name;
        this.price = price;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public Object clone() {
        // TODO Auto-generated method stub
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            return new Product(getId(), getName(), getPrice());
        }
    }

    
    
}
