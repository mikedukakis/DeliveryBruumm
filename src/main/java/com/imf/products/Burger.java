package com.imf.products;

public class Burger extends Product{
    public Burger(){
        super(8.9F);
    }

    @Override
    public void promotion() {
        System.out.println("Special promotion! You get a free cap with your Burger!");
    }
}
