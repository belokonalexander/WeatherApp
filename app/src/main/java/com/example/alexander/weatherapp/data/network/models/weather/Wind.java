package com.example.alexander.weatherapp.data.network.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;

    @SerializedName("deg")
    @Expose
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }
}
