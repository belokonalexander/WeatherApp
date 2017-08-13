package com.example.alexander.weatherapp.data.network.models.weather;

public class ForecastInfo {

    private long dt;

    private Main main;

    private Weather weather;

    public long getDt() {
        return dt;
    }

    public double getTemp() {
        return main.temp;
    }

    public int getWeatherId() {
        return weather.id;
    }

    private static class Main {
        double temp;
    }

    private static class Weather {
        int id;
    }
}
