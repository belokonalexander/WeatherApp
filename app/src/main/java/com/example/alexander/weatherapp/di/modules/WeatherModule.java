package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.business.weather.WeatherInteractorImpl;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.network.api.GooglePlacesApi;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.repositories.GooglePlacesApiRepository;
import com.example.alexander.weatherapp.data.repositories.GooglePlacesApiRepositoryImpl;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepositoryImpl;
import com.example.alexander.weatherapp.di.scopes.WeatherScope;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.presentation.add_city.AddCityPresenter;
import com.example.alexander.weatherapp.presentation.weather.WeatherPresenter;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {

    @Provides
    @WeatherScope
    WeatherApiRepository provideWeatherApiRepository(WeatherApi weatherApi) {
        return new WeatherApiRepositoryImpl(weatherApi);
    }

    @Provides
    @WeatherScope
    GooglePlacesApiRepository provideGooglePlacesApiRepository(GooglePlacesApi googlePlacesApi) {
        return new GooglePlacesApiRepositoryImpl(googlePlacesApi);
    }

    @Provides
    @WeatherScope
    WeatherInteractor provideWeatherInteractor(WeatherApiRepository repository,
                                               WeatherModelToCityWeatherMapper mapper,
                                               WeatherLocalRepository weatherLocalRepository,
                                               JobWrapper jw,
                                               GooglePlacesApiRepository googlePlacesApiRepository) {
        return new WeatherInteractorImpl(repository, mapper, weatherLocalRepository, jw, googlePlacesApiRepository);
    }


    @Provides
    WeatherPresenter provideWeatherPresenter(WeatherInteractor interactor, EventBus eventBus) {
        return new WeatherPresenter(interactor, eventBus);
    }

    @Provides
    AddCityPresenter provideAddCityPresenter(WeatherInteractor weatherInteractor) {
        return new AddCityPresenter(weatherInteractor);
    }
}
