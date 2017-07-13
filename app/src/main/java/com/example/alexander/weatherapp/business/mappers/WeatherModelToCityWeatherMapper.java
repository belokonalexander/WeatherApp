package com.example.alexander.weatherapp.business.mappers;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by Alexander on 13.07.2017.
 */

public class WeatherModelToCityWeatherMapper {

    public Function<WeatherModel, SingleSource<CityWeather>> toCityWeather(){
        return model -> Single.fromCallable(() ->
                new CityWeather(model.getName(), model.getMain().getTemp(), model.getMain().getPressure(),
                model.getMain().getHumidity()));
    }

}
