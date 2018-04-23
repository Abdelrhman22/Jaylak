package com.example.android.jaylak.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by Tech-4 on 2/21/2018.
 */

public class ProfileHistory {

    String name;
    String details;
    Drawable image;

    public ProfileHistory(String name, String details, Drawable image) {
        this.name = name;
        this.details = details;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Drawable getImage() {
        return image;
    }
}