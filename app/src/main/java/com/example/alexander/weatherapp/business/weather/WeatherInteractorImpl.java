package com.example.alexander.weatherapp.business.weather;

import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public class WeatherInteractorImpl implements WeatherInteractor {


    private WeatherApiRepository weatherApiRepository;


    public WeatherInteractorImpl(WeatherApiRepository weatherApiRepository) {
        this.weatherApiRepository = weatherApiRepository;
    }

    @Override
    public Single<WeatherModel> getWeather() {
        //TODO получиь ID текущего выбранного города
        return weatherApiRepository.getWeather("2172797");
    }

}
