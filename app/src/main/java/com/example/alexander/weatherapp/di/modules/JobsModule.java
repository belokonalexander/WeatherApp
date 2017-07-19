package com.example.alexander.weatherapp.di.modules;

import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.job.WeatherJobCreator;
import com.example.alexander.weatherapp.data.prefs.EventedSharedPrefs;
import com.example.alexander.weatherapp.data.prefs.SharedPrefs;

import dagger.Module;
import dagger.Provides;


@Module
public class JobsModule {

    @Provides
    JobManager provideJobManager(Context context) {
        return JobManager.create(context);
    }

    @Provides
    WeatherJobCreator provideWeatherJobCreator(WeatherApi weatherApi, WeatherModelToCityWeatherMapper mapper, EventedSharedPrefs prefs) {
        return new WeatherJobCreator(weatherApi, mapper, prefs);
    }

    @Provides
    JobWrapper provideJobWrapper(Context context, SharedPrefs sharedPrefs, JobManager jobManager) {
        return new JobWrapper(context, sharedPrefs, jobManager);
    }

}
