package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Wind {

    @SerializedName("speed")
    @Expose
    Double speed;

    @SerializedName("deg")
    @Expose
    Integer deg;

    public Double getSpeed() {
        return speed;
    }

    public Integer getDeg() {
        return deg;
    }
}
