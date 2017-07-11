package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.di.scopes.WeatherScope;
import com.example.alexander.weatherapp.presentation.weather.interfaces.IWeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander on 08.07.2017.
 */

@Module
public class WeatherModule {

    @Provides
    @WeatherScope
    IWeatherPresenter provideISettingstPresenter(){
        return new WeatherPresenter();
    }

}
