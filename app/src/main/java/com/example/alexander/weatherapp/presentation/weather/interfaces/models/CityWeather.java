package com.example.alexander.weatherapp.presentation.weather.interfaces.models;

/**
 * Created by Alexander on 13.07.2017.
 */

public class CityWeather {

    private String cityName;

    private Double temp;
    private Integer press;
    private Integer hum;

    public CityWeather(String cityName, Double temp, Integer press, Integer hum) {
        this.cityName = cityName;
        this.temp = temp;
        this.press = press;
        this.hum = hum;
    }

    public String getCityName() {
        return cityName;
    }


    public Double getTemp() {
        return temp;
    }

    public Integer getPress() {
        return press;
    }

    public Integer getHum() {
        return hum;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "cityName='" + cityName + '\'' +
                ", temp=" + temp +
                ", press=" + press +
                ", hum=" + hum +
                '}';
    }
}
