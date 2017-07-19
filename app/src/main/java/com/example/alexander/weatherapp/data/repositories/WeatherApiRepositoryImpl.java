package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import io.reactivex.Single;



public class WeatherApiRepositoryImpl implements WeatherApiRepository {

    private WeatherApi weatherApi;

    public WeatherApiRepositoryImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }


    @Override
    public Single<WeatherModel> getWeatherByName(String name) {
        return weatherApi.weatherByName(name);
    }


}
