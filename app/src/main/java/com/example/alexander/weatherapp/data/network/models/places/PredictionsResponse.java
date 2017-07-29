package com.example.alexander.weatherapp.data.network.models.places;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PredictionsResponse {

    @SerializedName("predictions")
    private List<Prediction> predictions;

    @SerializedName("status")
    private String status;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
