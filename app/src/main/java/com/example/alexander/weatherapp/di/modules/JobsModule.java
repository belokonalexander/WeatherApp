package com.example.alexander.weatherapp.di.modules;

import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.job.WeatherJobCreator;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class JobsModule {

    @Provides
    JobManager provideJobManager(Context context) {
        return JobManager.create(context);
    }

    @Provides
    WeatherJobCreator provideWeatherJobCreator(WeatherApi weatherApi,
                                               WeatherModelToCityWeatherMapper mapper,
                                               WeatherLocalRepository weatherLocalRepository,
                                               EventBus eventBus) {
        return new WeatherJobCreator(weatherApi, mapper, weatherLocalRepository, eventBus);
    }

    @Provides
    JobWrapper provideJobWrapper(Context context, SharedPrefsRepository sharedPrefs, JobManager jobManager) {
        return new JobWrapper(context, sharedPrefs, jobManager);
    }
}
