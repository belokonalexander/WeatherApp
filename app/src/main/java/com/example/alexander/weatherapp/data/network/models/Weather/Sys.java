package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Sys {

    @SerializedName("type")
    @Expose
    Integer type;

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("message")
    @Expose
    Double message;

    @SerializedName("country")
    @Expose
    String country;

    @SerializedName("sunrise")
    @Expose
    Integer sunrise;

    @SerializedName("sunset")
    @Expose
    Integer sunset;

    public Integer getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public Double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }
}
