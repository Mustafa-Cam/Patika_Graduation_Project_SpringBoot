package com.patika.paymentservice.model;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private long stock;

    public Product(String id, String name, String description, double price, long stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

}
