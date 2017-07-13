package com.example.alexander.weatherapp.presentation.weather.interfaces;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;
import com.example.alexander.weatherapp.presentation.exceptions.ViewException;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface WeatherPresenter {


     void handleSuccessGetWeather(WeatherModel iItems);
     void handleFailureGetWeather(Throwable throwable);
     void getWeather();

    void bindView(WeatherView weatherView);
    void unbindView();

}
