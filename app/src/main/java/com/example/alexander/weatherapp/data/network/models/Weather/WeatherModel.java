package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alexander on 13.07.2017.
 */

public class WeatherModel {

    @SerializedName("coord")
    @Expose
    public Coord coord;

    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;

    @SerializedName("base")
    @Expose
    public String base;

    @SerializedName("main")
    @Expose
    public Main main;

    @SerializedName("visibility")
    @Expose
    public Integer visibility;

    @SerializedName("wind")
    @Expose
    public Wind wind;

    @SerializedName("clouds")
    @Expose
    public Clouds clouds;

    @SerializedName("dt")
    @Expose
    public Integer dt;

    @SerializedName("sys")
    @Expose
    public Sys sys;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("cod")
    @Expose
    public Integer cod;

    @Override
    public String toString() {
        return "WeatherModel{" +
                "main=" + main +
                '}';
    }
}
