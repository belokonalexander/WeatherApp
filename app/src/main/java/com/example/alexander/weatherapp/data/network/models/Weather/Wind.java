package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Wind {

    @SerializedName("speed")
    @Expose
    public Double speed;

    @SerializedName("deg")
    @Expose
    public Integer deg;

}
