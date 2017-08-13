package com.example.alexander.weatherapp.data.network.models.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastModel {

    @SerializedName("list")
    private List<ForecastInfo> forecastInfo;

    public List<ForecastInfo> getForecastInfo() {
        return forecastInfo;
    }
}
