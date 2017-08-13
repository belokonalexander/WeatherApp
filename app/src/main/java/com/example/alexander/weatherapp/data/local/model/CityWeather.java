package com.example.alexander.weatherapp.data.local.model;

import java.util.Date;

import io.requery.Entity;
import io.requery.Key;

@Entity
public abstract class CityWeather {

    public static final int STATE_UNKNOWN = -1;
    public static final int STATE_RAIN = 0;
    public static final int STATE_CLOUD = 1;
    public static final int STATE_THUNDERSTORM = 2;
    public static final int STATE_SNOW = 3;
    public static final int STATE_CLEAR = 5;

    public static CityWeather newInstance(int cityId, int weatherState, String cityName, double temp, double press, int hum) {
        CityWeather cityWeather = new CityWeatherEntity();
        cityWeather.cityId = cityId;
        cityWeather.weatherState = weatherState;
        cityWeather.cityName = cityName;
        cityWeather.temp = temp;
        cityWeather.press = press;
        cityWeather.hum = hum;
        cityWeather.createdDate = new Date();
        return cityWeather;
    }

    public static CityWeather getNullable() {
        return newInstance(-1, -1, "", -1d, -1d, -1);
    }

    public static boolean isNullable(CityWeather cityWeather) {
        return cityWeather == null || cityWeather.equals(getNullable());
    }

    @Key
    int cityId;

    int weatherState;

    String cityName;

    double temp;

    double press;

    int hum;

    Date createdDate;

    public abstract int getCityId();

    public abstract int getWeatherState();

    public abstract String getCityName();

    public abstract double getTemp();

    public abstract double getPress();

    public abstract int getHum();

    public abstract Date getCreatedDate();
}
