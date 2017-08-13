package com.example.alexander.weatherapp.business.mappers;

import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.weather.Weather;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;


public class WeatherModelToCityWeatherMapper {

    public Function<WeatherModel, SingleSource<CityWeather>> toCityWeather(String cityName) {
        return model -> Single.fromCallable(() ->
                CityWeather.newInstance(
                        model.getId(),
                        getStateCode(model.getWeather()),
                        cityName,
                        model.getMain().getTemp(),
                        model.getMain().getPressure(),
                        model.getMain().getHumidity()));
    }

    private int getStateCode(List<Weather> weathers) {

        int code = CityWeather.STATE_UNKNOWN;

        if (weathers != null && !weathers.isEmpty()) {
            int id = weathers.get(0).getId();
            if (id == 800) {
                code = CityWeather.STATE_CLEAR;
            } else
                switch (id / 100) {
                    case 2:
                        code = CityWeather.STATE_THUNDERSTORM;
                        break;
                    case 5:
                        code = CityWeather.STATE_RAIN;
                        break;
                    case 6:
                        code = CityWeather.STATE_SNOW;
                        break;
                    case 8:
                        code = CityWeather.STATE_CLOUD;
                        break;
                }
        }

        return code;
    }
}
