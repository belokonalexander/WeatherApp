package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.weather.Forecast;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import java.util.List;

import io.reactivex.Single;


public interface WeatherApiRepository {

    Single<WeatherModel> getWeatherByName(String name);

    Single<WeatherModel> getWeatherById(int id);

    Single<WeatherModel> getWeatherByLocation(Location location);

    Single<List<Forecast>> getForecastByCityId(int cityId);
}
