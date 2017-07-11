package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.di.scopes.SettingsScope;
import com.example.alexander.weatherapp.presentation.settings.SettingsFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = SettingsModule.class)
@SettingsScope
public interface SettingsComponent {

    void inject(SettingsFragment settingsFragment);

}
