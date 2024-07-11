package com.imf.products;

public abstract class Product {
    private float price;

    public Product(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product:\n" +
                "Product: " + Product.class.getName() + "\n" +
                "price: " + price + "\n";
    }

    public abstract void promotion();
}
