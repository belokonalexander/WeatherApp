package com.example.alexander.weatherapp.Presentation.Weather.Interfaces;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface IWeatherPresenter {

    void bindView(IWeatherView weatherView);
    void unbindView();

}
