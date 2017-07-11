package com.example.alexander.weatherapp.presentation.weather;

import com.example.alexander.weatherapp.presentation.weather.interfaces.IWeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.interfaces.IWeatherView;

/**
 * Created by Alexander on 08.07.2017.
 */

public class WeatherPresenter implements IWeatherPresenter {

    private IWeatherView view;

    @Override
    public void bindView(IWeatherView weatherView) {
        this.view = weatherView;
    }

    @Override
    public void unbindView() {
        view = null;
    }
}
