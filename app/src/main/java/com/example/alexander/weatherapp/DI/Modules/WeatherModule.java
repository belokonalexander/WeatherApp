package com.example.alexander.weatherapp.DI.Modules;

import com.example.alexander.weatherapp.DI.Scopes.WeatherScope;
import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherPresenter;
import com.example.alexander.weatherapp.Presentation.Weather.WeatherPresenter;

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
