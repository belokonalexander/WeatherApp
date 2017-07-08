package com.example.alexander.weatherapp.DI.Components;

import com.example.alexander.weatherapp.DI.Modules.SettingsModule;
import com.example.alexander.weatherapp.DI.Scopes.AboutScope;
import com.example.alexander.weatherapp.DI.Scopes.SettingsScope;
import com.example.alexander.weatherapp.Presentation.About.AboutFragment;
import com.example.alexander.weatherapp.Presentation.Settings.SettingsFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = SettingsModule.class)
@SettingsScope
public interface SettingsComponent {

    void inject(SettingsFragment settingsFragment);

}
