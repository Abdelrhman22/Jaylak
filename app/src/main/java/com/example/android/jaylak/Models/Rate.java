package com.example.android.jaylak.Models;

/**
 * Created by Tech-4 on 3/26/2018.
 */

public class Rate {

    private String image;
    private String name;
    private float foodRate;
    private float serviceRate;
    private float locationRate;
    private float qualityRate;

    public Rate(String image, String name, int foodRate, int serviceRate, int locationRate, int qualityRate) {
        this.image = image;
        this.name = name;
        this.foodRate = foodRate;
        this.serviceRate = serviceRate;
        this.locationRate = locationRate;
        this.qualityRate = qualityRate;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public float getFoodRate() {
        return foodRate;
    }

    public float getServiceRate() {
        return serviceRate;
    }

    public float getLocationRate() {
        return locationRate;
    }

    public float getQualityRate() {
        return qualityRate;
    }
}