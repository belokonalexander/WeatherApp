package com.example.alexander.weatherapp.DI.Modules;

import com.example.alexander.weatherapp.DI.Scopes.SettingsScope;
import com.example.alexander.weatherapp.Presentation.Settings.Interfaces.ISettingsPresenter;
import com.example.alexander.weatherapp.Presentation.Settings.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander on 08.07.2017.
 */

@Module
public class SettingsModule {

    @Provides
    @SettingsScope
    ISettingsPresenter provideISettingstPresenter(){
        return new SettingsPresenter();
    }

}
