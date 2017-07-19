package com.example.alexander.weatherapp.data.network.api;

import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherApi {

    @GET("weather")
    Single<WeatherModel> weatherByName(@Query("q") String cityName);

}
