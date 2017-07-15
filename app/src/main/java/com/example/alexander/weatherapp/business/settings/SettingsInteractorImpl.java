package com.example.alexander.weatherapp.business.settings;

import com.example.alexander.weatherapp.data.local.JobWrapper;

import io.reactivex.Single;

/**
 * Created by Alexander on 14.07.2017.
 */

public class SettingsInteractorImpl implements SettingsInteractor {

    private JobWrapper jobWrapper;

    public SettingsInteractorImpl(JobWrapper jobWrapper) {
        this.jobWrapper = jobWrapper;
    }

    @Override
    public Single<Boolean> startUpdateJob(boolean enabled) {
        return Single.fromCallable(() -> enabled ?  jobWrapper.tryToStartWeatherJob() : jobWrapper.disableWeatherJob());
    }

}
