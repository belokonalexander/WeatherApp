package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import io.reactivex.Single;



public interface WeatherApiRepository {

    Single<WeatherModel> getWeatherByName(String name);

}
