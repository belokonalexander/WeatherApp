package com.example.alexander.weatherapp.business.settings;

import io.reactivex.Single;



public interface SettingsInteractor {

    Single<Boolean> startUpdateJob(boolean enabled);

}
