package com.sam.springboot.reactor.app.models;

import java.util.ArrayList;
import java.util.List;

public class Comments {
    private List<String> coments;

    public Comments() {
        this.coments =  new ArrayList<>();
    }

    public void addComments(String comment){
        this.coments.add(comment);
    }

    @Override
    public String toString() {
        return "{" +
                "coments: " + coments +
                '}';
    }
}
