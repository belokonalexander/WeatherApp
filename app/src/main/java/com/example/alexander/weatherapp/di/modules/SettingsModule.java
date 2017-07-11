package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.di.scopes.SettingsScope;
import com.example.alexander.weatherapp.presentation.settings.intefaces.ISettingsPresenter;
import com.example.alexander.weatherapp.presentation.settings.SettingsPresenter;

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
