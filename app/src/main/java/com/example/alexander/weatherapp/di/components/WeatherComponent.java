package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.di.scopes.WeatherScope;
import com.example.alexander.weatherapp.presentation.weather.WeatherFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = WeatherModule.class)
@WeatherScope
public interface WeatherComponent {

    void inject(WeatherFragment weatherFragment);

}
