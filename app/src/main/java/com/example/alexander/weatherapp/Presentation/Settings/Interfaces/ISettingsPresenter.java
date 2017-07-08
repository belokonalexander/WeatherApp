package com.example.alexander.weatherapp.Presentation.Settings.Interfaces;

import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherView;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface ISettingsPresenter {

    void bindView(ISettingsView settingsView);
    void unbindView();

}
