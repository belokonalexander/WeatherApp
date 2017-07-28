package com.example.alexander.weatherapp.data.network.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GooglePlacesApi {

    String URL = "https://maps.googleapis.com/maps/api/place/";

    String API_KEY = "AIzaSyAVMmeKPsNDmXehQ10hxqWDPoztpAHpOcQ";

    @GET("autocomplete/json?key=" + API_KEY)
    Single autocomplete(@Query("input") String input,
                        @Query("language") String lang);
}
