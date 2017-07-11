package com.example.alexander.weatherapp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.di.components.AppComponent;
import com.example.alexander.weatherapp.di.components.DaggerAppComponent;
import com.example.alexander.weatherapp.di.modules.AppModule;

/**
 * Created by Alexander on 08.07.2017.
 */

public class WeatherApplication extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    @NonNull
    public static WeatherApplication get(@NonNull Context context) {
        return (WeatherApplication) context.getApplicationContext();
    }

}
