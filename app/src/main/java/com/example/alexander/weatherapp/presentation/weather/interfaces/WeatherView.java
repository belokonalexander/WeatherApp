package com.example.alexander.weatherapp.presentation.weather.interfaces;

import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface WeatherView {

    void onError(Throwable cause);
    void showWeather(CityWeather weatherModel);

    void startProgress();
    void finishProgress();

}
