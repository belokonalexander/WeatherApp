package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.CityWeatherAdapter;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.di.modules.AboutModule;
import com.example.alexander.weatherapp.di.modules.AppModule;
import com.example.alexander.weatherapp.di.modules.JobsModule;
import com.example.alexander.weatherapp.di.modules.NetworkModule;
import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.presentation.add_city.AddCityPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alexander on 08.07.2017.
 */

@Component(modules = {AppModule.class, JobsModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    AboutComponent plus(AboutModule aboutModule);

    SettingsComponent plus(SettingsModule settingsModule);

    WeatherComponent plus(WeatherModule weatherModule);

    void inject(WeatherApplication app);

    void inject(AddCityPresenter addCityPresenter);

    void inject(CityWeatherAdapter cityWeatherAdapter);
}
