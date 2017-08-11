package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public interface WeatherInteractor {

    Observable<CityWeather> getWeather(boolean fresh);

    Single<CityWeather> getWeatherByLocation(Location location);

    Single<List<Prediction>> getAutocomplete(String query);

    Single<Location> getLocation(String placeId);

}
