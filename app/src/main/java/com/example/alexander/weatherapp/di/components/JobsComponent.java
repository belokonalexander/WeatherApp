package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.di.modules.JobsModule;
import com.example.alexander.weatherapp.di.scopes.JobsScope;

import dagger.Subcomponent;

/**
 * Created by Alexander on 14.07.2017.
 */
@Subcomponent(modules = JobsModule.class)
@JobsScope
public interface JobsComponent {

    void inject(WeatherApplication app);

}
