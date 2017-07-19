package com.example.alexander.weatherapp.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.network.NetworkService;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.prefs.EventedSharedPrefs;
import com.example.alexander.weatherapp.data.prefs.SharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module
public class AppModule {

    private Context appContext;


    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;

    }

    @Provides
    @Singleton
    Context provideContext(){
        return appContext;
    }

    @Provides
    @Singleton
    SharedPrefs provideSharedPrefs(){
        return new SharedPrefs(appContext);
    }

    @Provides
    @Singleton
    EventedSharedPrefs provideEventedSharedPrefs(){
        return new EventedSharedPrefs(appContext);
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(){
        return NetworkService.getService(appContext, WeatherApi.class);
    }


    @Provides
    WeatherModelToCityWeatherMapper provideMapper(){
        return new WeatherModelToCityWeatherMapper();
    }

}
