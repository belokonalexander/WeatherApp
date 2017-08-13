package com.example.alexander.weatherapp.data.network.api;

import com.example.alexander.weatherapp.data.network.models.weather.ForecastModel;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherApi {

    String URL = "http://api.openweathermap.org/data/2.5/";

    String APP_ID = "2080026a4d30007e744e576b22d43c06";

    @GET("weather?appid=" + APP_ID)
    Single<WeatherModel> weatherByName(@Query("q") String cityName);

    @GET("weather?appid=" + APP_ID)
    Single<WeatherModel> weatherById(@Query("id") int id);

    @GET("weather?appid=" + APP_ID)
    Single<WeatherModel> weatherByLocation(@Query("lat") double latitude,
                                           @Query("lon") double longitude);

    @GET("forecast?appid=" + APP_ID)
    Single<ForecastModel> forecastById(@Query("id") int id);
}
