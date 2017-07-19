package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public class WeatherInteractorImpl implements WeatherInteractor {


    private WeatherApiRepository weatherApiRepository;
    private SharedPrefsRepository sharedPrefsRepository;
    private WeatherModelToCityWeatherMapper weatherMapper;
    private JobWrapper jobWrapper;


    public WeatherInteractorImpl(WeatherApiRepository weatherApiRepository, WeatherModelToCityWeatherMapper mapper, SharedPrefsRepository sharedPrefs, JobWrapper jobWrapper) {
        this.weatherApiRepository = weatherApiRepository;
        this.weatherMapper = mapper;
        this.sharedPrefsRepository = sharedPrefs;
        this.jobWrapper = jobWrapper;
    }

    @Override
    public Observable<CityWeather> getWeather(boolean fresh) {
        //TODO получить ID текущего выбранного города

        List<Single<CityWeather>> tasks = new ArrayList<>();

        tasks.add(sharedPrefsRepository.getCityWeather());

        if (fresh) {
            tasks.add(weatherApiRepository.getWeatherByName("Moscow")
                    .flatMap(weatherMapper.toCityWeather())
                    .doOnSuccess(cityWeather -> {
                        sharedPrefsRepository.saveCityWeather(cityWeather);
                        jobWrapper.tryToStartWeatherJob();
                    }));
        }


        return Single.concat(tasks).toObservable();
    }


}
