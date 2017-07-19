package com.example.alexander.weatherapp.data.network.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Main {

    @SerializedName("temp")
    @Expose
    private double temp;

    @SerializedName("pressure")
    @Expose
    private int pressure;

    @SerializedName("humidity")
    @Expose
    private int humidity;

    @SerializedName("temp_min")
    @Expose
    private double tempMin;

    @SerializedName("temp_max")
    @Expose
    private double tempMax;

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}
