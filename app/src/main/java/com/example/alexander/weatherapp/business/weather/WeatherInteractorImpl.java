package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.job.WeatherJob;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public class WeatherInteractorImpl implements WeatherInteractor {


    private WeatherApiRepository weatherApiRepository;
    private WeatherModelToCityWeatherMapper weatherMapper;


    public WeatherInteractorImpl(WeatherApiRepository weatherApiRepository, WeatherModelToCityWeatherMapper mapper) {
        this.weatherApiRepository = weatherApiRepository;
        this.weatherMapper = mapper;

    }



    @Override
    public Single<CityWeather> getWeather() {
        //TODO получить ID текущего выбранного города

        return weatherApiRepository.getWeatherByName("Moscow")
                .doOnSuccess(weatherModel -> {
                    //задача на периодическое обновление
                    WeatherJob.scheduleJob();
                })
                .map(weatherModel -> {
                    LogUtils.write("WEATHER MODEL: " + weatherModel);
                    return weatherModel;
                })
                .flatMap(weatherMapper.toCityWeather());

    }

}
