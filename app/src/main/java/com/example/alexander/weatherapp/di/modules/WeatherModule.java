package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.di.scopes.WeatherScope;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.WeatherPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander on 08.07.2017.
 */

@Module
public class WeatherModule {

    @Provides
    @WeatherScope
    WeatherPresenter provideWeatherPresenter(){
        return new WeatherPresenterImpl();
    }

}
