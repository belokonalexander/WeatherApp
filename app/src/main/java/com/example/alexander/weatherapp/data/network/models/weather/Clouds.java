package com.example.alexander.weatherapp.data.network.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    public int getAll() {
        return all;
    }
}
