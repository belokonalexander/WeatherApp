package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.CityWeatherAdapter;
import com.example.alexander.weatherapp.NavigationActivity;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.di.scopes.WeatherScope;
import com.example.alexander.weatherapp.presentation.add_city.AddCityActivity;
import com.example.alexander.weatherapp.presentation.add_city.AddCityPresenter;
import com.example.alexander.weatherapp.presentation.weather.WeatherFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = WeatherModule.class)
@WeatherScope
public interface WeatherComponent {

    AddCityPresenter provideAddCityPresenter();

    void inject(WeatherFragment weatherFragment);

    void inject(AddCityActivity addCityActivity);

    void inject(CityWeatherAdapter cityWeatherAdapter);

    void inject(NavigationActivity navigationActivity);
}
