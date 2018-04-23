package com.example.android.jaylak.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 3/5/2018.
 */

public class BrandsList {

    @SerializedName("brands")
    @Expose
    private ArrayList<Brand> brands = new ArrayList<>();

    public ArrayList<Brand> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<Brand> brands) {
        this.brands = brands;
    }

}