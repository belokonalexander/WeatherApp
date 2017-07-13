package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Sys {

    @SerializedName("type")
    @Expose
    public Integer type;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("message")
    @Expose
    public Double message;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("sunrise")
    @Expose
    public Integer sunrise;

    @SerializedName("sunset")
    @Expose
    public Integer sunset;

}
