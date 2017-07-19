package com.example.alexander.weatherapp.data.network.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Coord {

    @SerializedName("lon")
    @Expose
    private double lon;

    @SerializedName("lat")
    @Expose
    private double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
