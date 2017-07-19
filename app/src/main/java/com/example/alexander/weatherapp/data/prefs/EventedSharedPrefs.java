package com.example.alexander.weatherapp.data.prefs;

import android.content.Context;

import com.example.alexander.weatherapp.events.StoreUpdatedEvent;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import org.greenrobot.eventbus.EventBus;


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
