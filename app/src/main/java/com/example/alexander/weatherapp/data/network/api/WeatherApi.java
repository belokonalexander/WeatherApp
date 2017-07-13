package com.example.alexander.weatherapp.data.network.api;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alexander on 13.07.2017.
 */

public interface WeatherApi {

    @GET("weather")
    Observable<WeatherModel> weatherById(@Query("id") String cityId);

    @GET("weather")
    Observable<WeatherModel> weatherByName(@Query("q") String cityName);

}
