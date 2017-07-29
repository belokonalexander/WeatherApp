package com.example.alexander.weatherapp.data.network.models.places;

import com.google.gson.annotations.SerializedName;

public class DetailsResponse {

    @SerializedName("result")
    private Place place;

    @SerializedName("status")
    private String status;


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
