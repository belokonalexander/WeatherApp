package com.example.alexander.weatherapp.data.network.api;

import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Alexander on 13.07.2017.
 */

public interface WeatherApi {

    @POST("weather")
    Observable<WeatherModel> weather(@Query("id") String cityId);


}
