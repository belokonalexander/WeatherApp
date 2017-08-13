package com.example.alexander.weatherapp.data.local;

import com.example.alexander.weatherapp.data.local.model.CityWeather;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface WeatherLocalRepository {

    Single<CityWeather> getCityWeather();

    Observable<List<? extends CityWeather>> getAllCityWeather();

    Single<CityWeather> saveCityWeather(CityWeather cityWeather);
}
