package com.example.android.jaylak.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tech-4 on 3/12/2018.
 */

public class CategoryItem {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("xthumbnail")
    @Expose
    private String image;

    @SerializedName("brands_count")
    @Expose
    private int count;

    public CategoryItem(String name, String image, int count) {
        this.name = name;
        this.image = image;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                '}';
    }
}