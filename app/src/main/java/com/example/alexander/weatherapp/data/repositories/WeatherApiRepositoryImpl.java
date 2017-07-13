package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public class WeatherApiRepositoryImpl implements WeatherApiRepository {

    private WeatherApi weatherApi;

    public WeatherApiRepositoryImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Single<WeatherModel> getWeatherByID(String id) {
        return Single.fromObservable(weatherApi.weatherById(id));
    }

    @Override
    public Single<WeatherModel> getWeatherByName(String name) {
        return Single.fromObservable(weatherApi.weatherByName(name));
    }


}
