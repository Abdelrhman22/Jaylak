package com.example.android.jaylak.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by Tech-4 on 2/14/2018.
 */

public class Interest {

    private String name;
    private Drawable image;

    public Interest(String name, Drawable image) {

        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }
}