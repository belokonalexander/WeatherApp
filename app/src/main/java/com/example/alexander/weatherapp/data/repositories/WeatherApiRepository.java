package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import io.reactivex.Single;


public interface WeatherApiRepository {

    Single<WeatherModel> getWeatherByName(String name);

    Single<WeatherModel> getWeatherById(int id);

    Single<WeatherModel> getWeatherByLocation(Location location);
}
