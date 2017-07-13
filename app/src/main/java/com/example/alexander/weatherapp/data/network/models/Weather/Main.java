package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Main {

    @SerializedName("temp")
    @Expose
    public Double temp;

    @SerializedName("pressure")
    @Expose
    public Integer pressure;

    @SerializedName("humidity")
    @Expose
    public Integer humidity;

    @SerializedName("temp_min")
    @Expose
    public Double tempMin;

    @SerializedName("temp_max")
    @Expose
    public Double tempMax;

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}
