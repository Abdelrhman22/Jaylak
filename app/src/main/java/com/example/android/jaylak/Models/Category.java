package com.example.android.jaylak.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tech-4 on 2/14/2018.
 */

public class Category {

    @SerializedName("data")
    @Expose
    private List<CategoryItem> data = new ArrayList<>();

    public List<CategoryItem> getData() {
        return data;
    }

    public void setData(List<CategoryItem> data) {
        this.data = data;
    }
}