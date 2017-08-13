package com.example.alexander.weatherapp.data.network.models.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastModel {

    @SerializedName("list")
    private List<Forecast> forecastList;

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
