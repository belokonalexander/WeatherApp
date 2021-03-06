package com.example.alexander.weatherapp;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.example.alexander.weatherapp.di.components.AppComponent;
import com.example.alexander.weatherapp.di.components.DaggerAppComponent;
import com.example.alexander.weatherapp.di.modules.AppModule;
import com.example.alexander.weatherapp.di.modules.NetworkModule;
import com.example.alexander.weatherapp.job.WeatherJobCreator;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;


public class WeatherApplication extends Application {

    private static AppComponent appComponent;

    @Inject
    WeatherJobCreator weatherJobCreator;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(this))
                .build();
        appComponent.inject(this);
        stethoInit(true);
        jobsInit();


    }


    private void jobsInit() {
        JobManager.create(this).addJobCreator(weatherJobCreator);
    }


    private void stethoInit(boolean enable) {
        if (enable) {
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
    }
}
