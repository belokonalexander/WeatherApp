package com.example.alexander.weatherapp.presentation.settings.interfaces;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface SettingsPresenter {

    void bindView(SettingsView settingsView);
    void unbindView();

    void handleSuccessJobStart(Boolean result);
    void handleFailureJobStart(Throwable throwable);

    void updateWeatherJob(boolean enabled);
}
