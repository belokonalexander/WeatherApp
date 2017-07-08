package com.example.alexander.weatherapp.Presentation.About.Interfaces;

import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherView;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface IAboutPresenter {

    void bindView(IAboutView aboutView);
    void unbindView();

}
