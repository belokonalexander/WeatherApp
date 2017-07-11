package com.example.alexander.weatherapp.presentation.settings;

import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsView;

/**
 * Created by Alexander on 08.07.2017.
 */

public class SettingsPresenterImpl implements com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsPresenter {

    private SettingsView view;

    @Override
    public void bindView(SettingsView settingsView) {
        this.view = settingsView;
    }

    @Override
    public void unbindView() {
        view = null;
    }
}
