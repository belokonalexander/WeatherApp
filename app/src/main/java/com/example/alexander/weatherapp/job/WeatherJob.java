package com.example.alexander.weatherapp.job;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.prefs.SharedPrefs;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Alexander on 14.07.2017.
 */

public class WeatherJob extends Job {

    public static final String TAG = "GET_WEATHER_JOB";


    private WeatherApi weatherApi;
    private WeatherModelToCityWeatherMapper mapper;
    private SharedPrefs sharedPrefs;

    public WeatherJob(WeatherApi weatherApi, WeatherModelToCityWeatherMapper mapper, SharedPrefs sharedPrefs) {
        this.weatherApi = weatherApi;
        this.mapper = mapper;
        this.sharedPrefs = sharedPrefs;
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {

        Single.fromObservable(weatherApi.weatherByName("Moscow"))
                .flatMap(mapper.toCityWeather())
                .subscribe(new DisposableSingleObserver<CityWeather>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull CityWeather cityWeather) {
                        LogUtils.write(" Android-job got new data: " + cityWeather);
                        sharedPrefs.setWeatherResult(cityWeather);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        LogUtils.write("Android-job got error: " + e);
                    }
                });


        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        new JobRequest.Builder(WeatherJob.TAG)
                .setRequiresCharging(true)                                  //задача выполняется только если телефон включен
                .setPersisted(true)                                         //задача невоспреимчива к перезагрузке устройства
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)   //задача выполняется при наличии интернет соединения
                .setUpdateCurrent(true)                                     //переписываю задачу с тем же тэгом
                .setPeriodic(TimeUnit.MINUTES.toMillis(15),TimeUnit.MINUTES.toMillis(14))
                .build()
                .schedule();
    }
}
