package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Main {

    @SerializedName("temp")
    @Expose
    Double temp;

    @SerializedName("pressure")
    @Expose
    Integer pressure;

    @SerializedName("humidity")
    @Expose
    Integer humidity;

    @SerializedName("temp_min")
    @Expose
    Double tempMin;

    @SerializedName("temp_max")
    @Expose
    Double tempMax;

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }
}
