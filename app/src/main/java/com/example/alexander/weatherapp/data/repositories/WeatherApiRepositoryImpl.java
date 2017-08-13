package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.weather.Forecast;
import com.example.alexander.weatherapp.data.network.models.weather.ForecastModel;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import java.util.List;

import io.reactivex.Single;

public class WeatherApiRepositoryImpl implements WeatherApiRepository {

    private WeatherApi weatherApi;

    public WeatherApiRepositoryImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Single<WeatherModel> getWeatherByName(String name) {
        return weatherApi.weatherByName(name);
    }

    @Override
    public Single<WeatherModel> getWeatherById(int id) {
        return weatherApi.weatherById(id);
    }

    @Override
    public Single<WeatherModel> getWeatherByLocation(Location location) {
        return weatherApi.weatherByLocation(location.getLatitude(), location.getLongitude());
    }

    @Override
    public Single<List<Forecast>> getForecastByCityId(int cityId) {
        return weatherApi.forecastById(cityId)
                .map(ForecastModel::getForecastList);
    }
}
