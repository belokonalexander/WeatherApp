package com.example.alexander.weatherapp.Presentation.Settings;

import com.example.alexander.weatherapp.Presentation.Settings.Interfaces.ISettingsPresenter;
import com.example.alexander.weatherapp.Presentation.Settings.Interfaces.ISettingsView;

/**
 * Created by Alexander on 08.07.2017.
 */

public class SettingsPresenter implements ISettingsPresenter {

    private ISettingsView view;

    @Override
    public void bindView(ISettingsView settingsView) {
        this.view = settingsView;
    }

    @Override
    public void unbindView() {
        view = null;
    }
}
