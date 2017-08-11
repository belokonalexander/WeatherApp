package com.example.alexander.weatherapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Single;


public class SharedPrefs implements SharedPrefsRepository {

    public static final String COMMON_PREFS = "common_prefs";
    public static final String COMMON_STORE = "common_store";

    public static final String _UPDATE_INTERVAL = "_update_interval";
    public static final String _AUTO_REFRESH = "_auto_refresh";
    public static final String _LAST_WEATHER_RESULT = "_last_weather_result";

    private SharedPreferences commonSettings;
    private SharedPreferences commonStore;
    private GsonBuilder gsonBuilder;
    private Context context;

    public SharedPrefs(Context context, GsonBuilder gsonBuilder) {
        this.context = context;
        this.gsonBuilder = gsonBuilder;
        commonSettings = context.getSharedPreferences(COMMON_PREFS, Context.MODE_PRIVATE);
        commonStore = context.getSharedPreferences(COMMON_STORE, Context.MODE_PRIVATE);
    }

    private void setWeatherResult(CityWeather cityWeather) {
        SharedPreferences.Editor editor = commonStore.edit();
        editor.putString(_LAST_WEATHER_RESULT, new GsonBuilder().create().toJson(cityWeather));
        editor.apply();
    }

    private CityWeather getWeatherResult() {
        String resultInJson = commonStore.getString(_LAST_WEATHER_RESULT, "");
        if (resultInJson.length() > 0) {
            Gson gson = gsonBuilder.create();
            return gson.fromJson(resultInJson, CityWeather.class);
        } else
            return null;
    }

    @Override
    public boolean getUpdateEnabled() {
        return commonSettings.getBoolean(_AUTO_REFRESH, false);
    }

    @Override
    public Single<CityWeather> getCityWeather() {
        return Single.fromCallable(this::getWeatherResult).onErrorReturnItem(CityWeather.getNullable());
    }

    @Override
    public void saveCityWeather(CityWeather cityWeather) {
        setWeatherResult(cityWeather);
    }

    @Override
    public int getUpdateInterval() {
        return Integer.parseInt(commonSettings.getString(_UPDATE_INTERVAL, context.getString(R.string.default_update_interval)));
    }
}
