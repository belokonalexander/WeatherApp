package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import io.reactivex.Single;



public interface SharedPrefsRepository {

    Single<CityWeather> getCityWeather();
    void saveCityWeather(CityWeather cityWeather);

}
