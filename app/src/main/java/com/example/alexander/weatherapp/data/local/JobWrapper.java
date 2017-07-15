package com.example.alexander.weatherapp.data.local;

import com.evernote.android.job.JobManager;
import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.job.WeatherJob;
import com.example.alexander.weatherapp.prefs.SharedPrefs;

/**
 * Created by Alexander on 14.07.2017.
 */

public class JobWrapper {

    private SharedPrefs sharedPrefs;
    private JobManager jobManager;

    public JobWrapper(SharedPrefs sharedPrefs, JobManager jobManager) {
        this.sharedPrefs = sharedPrefs;
        this.jobManager = jobManager;
    }

    public boolean tryToStartWeatherJob(){

        if(sharedPrefs.getUpdateEnabled()) {
            LogUtils.write("Enable weather job");
            WeatherJob.scheduleJob(sharedPrefs.getUpdateInterval());
            return true;
        };

        return false;
    }

    public Boolean disableWeatherJob() {
        LogUtils.write("Disable weather job");
        jobManager.cancelAllForTag(WeatherJob.TAG);
        return true;
    }
}
