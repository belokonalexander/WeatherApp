package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.business.settings.SettingsInteractor;
import com.example.alexander.weatherapp.business.settings.SettingsInteractorImpl;
import com.example.alexander.weatherapp.di.scopes.SettingsScope;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.presentation.settings.SettingsPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class SettingsModule {

    @Provides
    @SettingsScope
    SettingsPresenter provideSettingsPresenter(SettingsInteractor interactor) {
        return new SettingsPresenter(interactor);
    }

    @Provides
    @SettingsScope
    SettingsInteractor provideSettingsInteractor(JobWrapper jobWrapper) {
        return new SettingsInteractorImpl(jobWrapper);
    }
}
