package com.example.alexander.weatherapp.business.weather;

import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.data.network.models.Weather.Weather;
import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;

import io.reactivex.Single;

/**
 * Created by Alexander on 13.07.2017.
 */

public interface WeatherInteractor {

    /**
     * получить погоду по id города
     * @return
     */
    Single<WeatherModel> getWeather();

}
