package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Weather {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("main")
    @Expose
    String main;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("icon")
    @Expose
    String icon;

    public Integer getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
