package com.example.android.jaylak.Models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jaylak.R;

/**
 * Created by Tech-4 on 3/19/2018.
 */

public class BrandFeature {

    private String name;
    private String image;

    public BrandFeature(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}