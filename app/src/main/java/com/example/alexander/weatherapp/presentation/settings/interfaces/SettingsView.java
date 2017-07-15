package com.example.alexander.weatherapp.presentation.settings.interfaces;

/**
 * Created by Alexander on 08.07.2017.
 */

public interface SettingsView {

    void jobStateChanged(boolean enabled);
    void onJobError(Throwable cause);

}
