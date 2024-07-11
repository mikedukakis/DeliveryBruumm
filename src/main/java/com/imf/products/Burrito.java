package com.imf.products;

public class Burrito extends Product{
    public Burrito() {
        super(6.5F);
    }

    @Override
    public void promotion() {
        System.out.println("Special promotion! You get a free pin with your Burrito!");
    }
}
