package com.example.android.jaylak.Models;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tech-4 on 2/14/2018.
 */

public class Brand {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("rate")
    @Expose
    private int rate;

    @SerializedName("image")
    @Expose
    private Drawable image;

    public Brand(String name, String type, int rate, Drawable image) {
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getRate() {
        return rate;
    }

    public Drawable getImage() {
        return image;
    }
}