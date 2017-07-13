package com.example.alexander.weatherapp.presentation.weather.interfaces;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface WeatherView {

    void onError(Throwable cause);
    void showWeather(WeatherModel weatherModel);

    void startProgress();
    void finishProgress();

}
