package com.example.asimplecafe.model;

public class Product {
    String name;

    String imageURI; int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        imageURI = "";
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageURI() {
        return imageURI;
    }
}
