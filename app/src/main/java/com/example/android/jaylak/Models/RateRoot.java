package com.example.android.jaylak.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tech-4 on 3/26/2018.
 */

public class RateRoot {

    @SerializedName("data")
    @Expose
    private List<Rate> rateArrayList = new ArrayList<>();

    public List<Rate> getRateArrayList() {
        return rateArrayList;
    }
}
