package com.example.alexander.weatherapp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.di.components.AppComponent;
import com.example.alexander.weatherapp.di.components.DaggerAppComponent;
import com.example.alexander.weatherapp.di.modules.AppModule;
import com.facebook.stetho.Stetho;

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
        stethoInit();
    }

    private void stethoInit() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    @NonNull
    public static WeatherApplication get(@NonNull Context context) {
        return (WeatherApplication) context.getApplicationContext();
    }

}
