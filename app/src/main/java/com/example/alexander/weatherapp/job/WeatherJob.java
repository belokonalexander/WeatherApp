package com.example.alexander.weatherapp.job;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.prefs.SharedPrefs;
import com.example.alexander.weatherapp.events.StoreUpdatedEvent;
import com.example.alexander.weatherapp.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.observers.DisposableSingleObserver;


class WeatherJob extends Job {

    static final String TAG = "GET_WEATHER_JOB";


    private final WeatherApi weatherApi;
    private final WeatherModelToCityWeatherMapper mapper;
    private final WeatherLocalRepository weatherLocalRepository;
    private final EventBus eventBus;

    WeatherJob(WeatherApi weatherApi,
               WeatherModelToCityWeatherMapper mapper,
               WeatherLocalRepository weatherLocalRepository,
               EventBus eventBus) {
        this.weatherApi = weatherApi;
        this.mapper = mapper;
        this.weatherLocalRepository = weatherLocalRepository;
        this.eventBus = eventBus;
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {

        final boolean[] flag = {false};

        weatherLocalRepository
                .getCityWeather()
                .map(CityWeather::getCityId)
                .flatMap(weatherApi::weatherById)
                .flatMap(mapper.toCityWeather(""))
                .subscribe(new DisposableSingleObserver<CityWeather>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull CityWeather cityWeather) {
                        LogUtils.writeLogCache(getContext(), WeatherJob.this.getClass(), " Android-job got new data: " + cityWeather);
                        weatherLocalRepository.saveCityWeather(cityWeather);
                        eventBus.post(new StoreUpdatedEvent(SharedPrefs.LAST_WEATHER_RESULT));
                        flag[0] = true;
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        LogUtils.writeLogCache(getContext(), WeatherJob.this.getClass(), "Android-job got error: " + e);
                        flag[0] = false;
                    }
                });

        LogUtils.writeLogCache(getContext(), "RESULT", "Android-job result: " + flag[0]);
        return flag[0] ? Result.SUCCESS : Result.FAILURE;
    }

    static void scheduleJob(int minutes) {

        new JobRequest.Builder(WeatherJob.TAG)
                //.setRequiresCharging(true)
                .setPersisted(true)                                          //задача невоспреимчива к перезагрузке устройства
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)   //задача выполняется при наличии интернет соединения
                .setUpdateCurrent(true)                                     //переписываю задачу с тем же тэгом
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(14))  //чтобы выполнение задачи было отложенным
                .build()
                .schedule();
    }
}
