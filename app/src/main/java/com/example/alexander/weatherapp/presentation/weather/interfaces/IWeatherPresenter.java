package com.example.alexander.weatherapp.presentation.weather.interfaces;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface IWeatherPresenter {

    void bindView(IWeatherView weatherView);
    void unbindView();

}
