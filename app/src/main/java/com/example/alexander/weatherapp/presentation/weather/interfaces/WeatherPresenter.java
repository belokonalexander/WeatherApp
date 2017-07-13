package com.example.alexander.weatherapp.presentation.weather.interfaces;

import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface WeatherPresenter {


     void handleSuccessGetWeather(CityWeather weather);
     void handleFailureGetWeather(Throwable throwable);
     void getWeather();

    void bindView(WeatherView weatherView);
    void unbindView();

}
