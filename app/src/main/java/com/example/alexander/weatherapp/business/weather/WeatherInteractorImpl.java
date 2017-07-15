package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.prefs.SharedPrefs;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public class WeatherInteractorImpl implements WeatherInteractor {


    private WeatherApiRepository weatherApiRepository;
    private WeatherModelToCityWeatherMapper weatherMapper;
    private SharedPrefs sharedPrefs;
    private JobWrapper jobWrapper;


    public WeatherInteractorImpl(WeatherApiRepository weatherApiRepository, WeatherModelToCityWeatherMapper mapper, SharedPrefs sharedPrefs, JobWrapper jobWrapper) {
        this.weatherApiRepository = weatherApiRepository;
        this.weatherMapper = mapper;
        this.sharedPrefs = sharedPrefs;
        this.jobWrapper = jobWrapper;
    }

    @Override
    public Single<CityWeather> getWeather() {
        //TODO получить ID текущего выбранного города

        return weatherApiRepository.getWeatherByName("Moscow")
                .flatMap(weatherMapper.toCityWeather())
                .doOnSuccess(weatherModel -> {
                    //задача на периодическое обновление
                    sharedPrefs.setWeatherResult(weatherModel);
                    jobWrapper.tryToStartWeatherJob();
                });
    }



    @Override
    public Single<CityWeather> getStoredWeather() {
        return Single.fromCallable(() -> {
                    //RxJava 2.x no longer accepts null values. Как лучше маскировать null value?
                    return sharedPrefs.getWeatherResult();
                });
    }


}
