package com.example.android.jaylak.Models;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by Tech-4 on 2/21/2018.
 */

public class ProfileAction {

    String name;
    String date;
    Drawable image;
    Drawable actionImage;

    public ProfileAction(String name, String date, Drawable image, Drawable actionImage) {
        this.name = name;
        this.date = date;
        this.image = image;
        this.actionImage = actionImage;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Drawable getImage() {
        return image;
    }

    public Drawable getActionImage() {
        return actionImage;
    }
}