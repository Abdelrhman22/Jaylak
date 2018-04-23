package com.example.android.jaylak.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by Tech-4 on 2/19/2018.
 */

public class StoreProduct {

    private String name;
    private String type;
    private int quantity;
    private double price;


    private Drawable image;

    public StoreProduct(String name, String type, int quantity, double price, Drawable image) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Drawable getImage() {
        return image;
    }

}