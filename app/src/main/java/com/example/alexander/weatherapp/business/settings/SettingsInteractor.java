package com.example.alexander.weatherapp.business.settings;

import io.reactivex.Single;

/**
 * Created by Alexander on 14.07.2017.
 */

public interface SettingsInteractor {

    Single<Boolean> startUpdateJob(boolean enabled);

}
