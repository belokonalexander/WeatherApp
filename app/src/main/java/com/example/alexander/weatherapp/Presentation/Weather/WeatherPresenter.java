package com.example.alexander.weatherapp.Presentation.Weather;

import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherPresenter;
import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherView;

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
