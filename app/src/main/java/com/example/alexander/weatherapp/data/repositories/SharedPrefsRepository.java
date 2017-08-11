package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.local.model.CityWeather;

import io.reactivex.Single;


public interface SharedPrefsRepository {

    Single<CityWeather> getCityWeather();

    void saveCityWeather(CityWeather cityWeather);

    int getUpdateInterval();

    boolean getUpdateEnabled();
}
