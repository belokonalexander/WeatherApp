package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public interface WeatherInteractor {

    Observable<CityWeather> getWeather(boolean fresh);

    Single<List<Prediction>> getAutocomplete(String query);
}
