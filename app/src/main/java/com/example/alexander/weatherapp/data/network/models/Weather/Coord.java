package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Coord {

    @SerializedName("lon")
    @Expose
    public Double lon;

    @SerializedName("lat")
    @Expose
    public Double lat;

}
