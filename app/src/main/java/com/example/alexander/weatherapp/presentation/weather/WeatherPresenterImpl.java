package com.example.alexander.weatherapp.presentation.weather;

import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherView;

/**
 * Created by Alexander on 08.07.2017.
 */

public class WeatherPresenterImpl implements WeatherPresenter {

    private WeatherView view;

    @Override
    public void bindView(WeatherView weatherView) {
        this.view = weatherView;
    }

    @Override
    public void unbindView() {
        view = null;
    }
}
