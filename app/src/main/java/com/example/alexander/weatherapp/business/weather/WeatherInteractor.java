package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public interface WeatherInteractor {

    /**
     * получить погоду по id города
     * @return
     */
    Single<CityWeather> getWeather();

    Single<CityWeather> getStoredWeather();

}
