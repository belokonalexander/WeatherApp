package com.example.alexander.weatherapp.job;

import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.example.alexander.weatherapp.utils.LogUtils;


public class JobWrapper {

    private SharedPrefsRepository sharedPrefs;
    private JobManager jobManager;
    private Context context;

    public JobWrapper(Context context, SharedPrefsRepository sharedPrefs, JobManager jobManager) {
        this.sharedPrefs = sharedPrefs;
        this.jobManager = jobManager;
        this.context = context;
    }

    public boolean tryToStartWeatherJob() {

        if (sharedPrefs.getUpdateEnabled()) {
            LogUtils.writeLogCache(context, getClass(), " Enable weather job: " + WeatherJob.TAG + "/ interval: " + sharedPrefs.getUpdateInterval());
            WeatherJob.scheduleJob(sharedPrefs.getUpdateInterval());
            return true;
        }

        return false;
    }

    public Boolean disableWeatherJob() {
        jobManager.cancelAllForTag(WeatherJob.TAG);
        LogUtils.writeLogCache(context, getClass(), " Disable weather job: " + WeatherJob.TAG);
        return true;
    }
}
