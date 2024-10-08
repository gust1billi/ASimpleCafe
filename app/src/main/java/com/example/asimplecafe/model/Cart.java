package com.example.asimplecafe.model;

import java.io.Serializable;

public class Cart implements Serializable {

    String name; int price, qty;

    public Cart(String name, int price, int qty) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public Cart(String name, int price) {
        this.name = name;
        this.price = price;
        this.qty = 1;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void addQuantity() {
        this.qty++;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
