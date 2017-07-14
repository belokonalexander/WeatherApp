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
    Coord coord;

    @SerializedName("weather")
    @Expose
    List<Weather> weather = null;

    @SerializedName("base")
    @Expose
    String base;

    @SerializedName("main")
    @Expose
    Main main;

    @SerializedName("visibility")
    @Expose
    Integer visibility;

    @SerializedName("wind")
    @Expose
    Wind wind;

    @SerializedName("clouds")
    @Expose
    Clouds clouds;

    @SerializedName("dt")
    @Expose
    Integer dt;

    @SerializedName("sys")
    @Expose
    Sys sys;

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("cod")
    @Expose
    Integer cod;

    @Override
    public String toString() {
        return "WeatherModel{" +
                "main=" + weather +
                '}';
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }
}
