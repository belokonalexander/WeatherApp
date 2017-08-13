package com.example.alexander.weatherapp.data.network.models.weather;

import java.util.List;

public class Forecast {

    private long dt;

    private Main main;

    private List<Weather> weather;

    public long getDt() {
        return dt;
    }

    public double getTemp() {
        return main.temp;
    }

    public int getWeatherId() {
        return weather.get(0).id;
    }

    private static class Main {
        double temp;
    }

    private static class Weather {
        int id;
    }
}
