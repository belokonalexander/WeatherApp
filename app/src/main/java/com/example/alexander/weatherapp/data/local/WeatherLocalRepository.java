package com.example.alexander.weatherapp.data.local;

import com.example.alexander.weatherapp.data.local.model.CityWeather;

import io.reactivex.Single;

public interface WeatherLocalRepository {

    Single<CityWeather> getCityWeather();

    Single<CityWeather> saveCityWeather(CityWeather cityWeather);
}
