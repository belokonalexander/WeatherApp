package com.example.alexander.weatherapp.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Alexander on 12.07.2017.
 */

public class SharedPrefs {

    public static final String COMMON_PREFS = "common_prefs";
    public static final String COMMON_STORE = "common_store";

    public static final String _UPDATE_INTERVAL = "_update_interval";
    public static final String _AUTO_REFRESH = "_auto_refresh";
    public static final String _LAST_WEATHER_RESULT = "_last_weather_result";

    private SharedPreferences commonSettings;
    private SharedPreferences commonStore;

    public SharedPrefs(Context context)
    {
        commonSettings = context.getSharedPreferences(COMMON_PREFS,Context.MODE_PRIVATE);
        commonStore = context.getSharedPreferences(COMMON_STORE,Context.MODE_PRIVATE);
    }

    public void setWeatherResult(CityWeather cityWeather){
        SharedPreferences.Editor editor = commonStore.edit();
        GsonBuilder builder = new GsonBuilder();
        editor.putString(_LAST_WEATHER_RESULT, new GsonBuilder().create().toJson(cityWeather));
        editor.apply();
    }

    public CityWeather getWeatherResult(){
        String resultInJson = commonStore.getString(_LAST_WEATHER_RESULT, "");
        if(resultInJson.length()>0){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.fromJson(resultInJson, CityWeather.class);
        } else
            return null;
    }

    public SharedPreferences getCommonSettings() {
        return commonSettings;
    }
}
