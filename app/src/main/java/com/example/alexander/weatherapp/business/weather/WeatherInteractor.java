package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import io.reactivex.Observable;


public interface WeatherInteractor {

    Observable<CityWeather> getWeather(boolean fresh);

}
