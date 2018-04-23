package com.example.android.jaylak.Models;

/**
 * Created by Tech-4 on 3/19/2018.
 */

public class BrandCategory {

    private String name;
    private String description;
    private String image;

    public BrandCategory(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}