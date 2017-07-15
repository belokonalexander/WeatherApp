package com.example.alexander.weatherapp.prefs;

import android.content.Context;

import com.example.alexander.weatherapp.events.StoreUpdatedEvent;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Alexander on 15.07.2017.
 */

public class EventedSharedPrefs extends SharedPrefs {
    public EventedSharedPrefs(Context context) {
        super(context);
    }

    @Override
    public void setWeatherResult(CityWeather cityWeather) {
        super.setWeatherResult(cityWeather);
        EventBus.getDefault().post(new StoreUpdatedEvent(_LAST_WEATHER_RESULT));
    }
}
