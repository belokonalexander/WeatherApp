package com.example.alexander.weatherapp.DI.Components;

import com.example.alexander.weatherapp.DI.Modules.WeatherModule;
import com.example.alexander.weatherapp.DI.Scopes.AboutScope;
import com.example.alexander.weatherapp.DI.Scopes.WeatherScope;
import com.example.alexander.weatherapp.Presentation.About.AboutFragment;
import com.example.alexander.weatherapp.Presentation.Weather.WeatherFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = WeatherModule.class)
@WeatherScope
public interface WeatherComponent {

    void inject(WeatherFragment weatherFragment);

}
