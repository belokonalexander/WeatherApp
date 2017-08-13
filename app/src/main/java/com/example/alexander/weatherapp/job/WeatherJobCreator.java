package com.example.alexander.weatherapp.job;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;

import org.greenrobot.eventbus.EventBus;


public class WeatherJobCreator implements JobCreator {

    private final WeatherApi weatherApi;
    private final WeatherModelToCityWeatherMapper mapper;
    private final WeatherLocalRepository weatherLocalRepository;
    private final EventBus eventBus;

    public WeatherJobCreator(WeatherApi weatherApi,
                             WeatherModelToCityWeatherMapper mapper,
                             WeatherLocalRepository weatherLocalRepository,
                             EventBus eventBus) {
        this.weatherApi = weatherApi;
        this.mapper = mapper;
        this.weatherLocalRepository = weatherLocalRepository;
        this.eventBus = eventBus;
    }

    @Override
    public Job create(String tag) {
        switch (tag) {
            case WeatherJob.TAG:
                //error fix Job for tag GET_WEATHER_JOB was already run, a creator should always create a new Job instance
                return new WeatherJob(weatherApi, mapper, weatherLocalRepository, eventBus);
            default:
                return null;
        }

    }
}
