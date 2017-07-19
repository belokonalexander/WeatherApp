package com.example.alexander.weatherapp.job;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.prefs.EventedSharedPrefs;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;
import com.example.alexander.weatherapp.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.observers.DisposableSingleObserver;


class WeatherJob extends Job {

    static final String TAG = "GET_WEATHER_JOB";


    private WeatherApi weatherApi;
    private WeatherModelToCityWeatherMapper mapper;
    private EventedSharedPrefs sharedPrefs;

    WeatherJob(WeatherApi weatherApi, WeatherModelToCityWeatherMapper mapper, EventedSharedPrefs sharedPrefs) {

        this.weatherApi = weatherApi;
        this.mapper = mapper;
        this.sharedPrefs = sharedPrefs;
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {

        //синхронизирую выполняение rx элемента с результатом функции
        //результат необходимо получать в зависимости от выполнения цепочки
        final boolean[] flag = {false};

        weatherApi.weatherByName("Moscow")
                .flatMap(mapper.toCityWeather())
                .subscribe(new DisposableSingleObserver<CityWeather>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull CityWeather cityWeather) {
                        LogUtils.writeLogCache(getContext(), WeatherJob.this.getClass(), " Android-job got new data: " + cityWeather);
                        sharedPrefs.setWeatherResult(cityWeather);
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
                .setPeriodic(TimeUnit.MINUTES.toMillis(minutes + 5), TimeUnit.MINUTES.toMillis(5))  //чтобы выполнение задачи было отложенным
                .build()
                .schedule();
    }
}
