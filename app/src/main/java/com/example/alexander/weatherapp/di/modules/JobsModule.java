package com.example.alexander.weatherapp.di.modules;

import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.JobWrapper;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.job.WeatherJobCreator;
import com.example.alexander.weatherapp.prefs.SharedPrefs;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander on 08.07.2017.
 */

@Module
public class JobsModule {

    @Provides
    JobManager provideJobManager(Context context){
        return JobManager.create(context);
    }

    @Provides
    WeatherJobCreator provideWeatherJobCreator(WeatherApi weatherApi, WeatherModelToCityWeatherMapper mapper, SharedPrefs prefs){
        return new WeatherJobCreator(weatherApi,mapper,prefs);
    }

    @Provides
    JobWrapper provideJobWrapper(SharedPrefs sharedPrefs, JobManager jobManager){
        return new JobWrapper(sharedPrefs, jobManager);
    }

}
