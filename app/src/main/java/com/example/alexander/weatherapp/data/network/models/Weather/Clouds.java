package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Clouds {

    @SerializedName("all")
    @Expose
    Integer all;

    public Integer getAll() {
        return all;
    }
}
