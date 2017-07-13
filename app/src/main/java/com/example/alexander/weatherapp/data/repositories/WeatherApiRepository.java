package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public interface WeatherApiRepository {

    Single<WeatherModel> getWeatherByID(String id);

    Single<WeatherModel> getWeatherByName(String id);

}
