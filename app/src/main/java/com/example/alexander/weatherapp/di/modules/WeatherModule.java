package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.business.weather.WeatherInteractorImpl;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepositoryImpl;
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
    WeatherApiRepository provideWeatherApiRepository(WeatherApi weatherApi){
        return new WeatherApiRepositoryImpl(weatherApi);
    }

    @Provides
    @WeatherScope
    WeatherInteractor provideWeatherInteractor(WeatherApiRepository repository){
        return new WeatherInteractorImpl(repository);
    }


    @Provides
    @WeatherScope
    WeatherPresenter provideWeatherPresenter(WeatherInteractor interactor){
        return new WeatherPresenterImpl(interactor);
    }

}
