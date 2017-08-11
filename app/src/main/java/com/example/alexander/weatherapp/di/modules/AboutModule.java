package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.di.scopes.AboutScope;
import com.example.alexander.weatherapp.presentation.about.AboutPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class AboutModule {

    @Provides
    @AboutScope
    AboutPresenter provideAboutPresenter() {
        return new AboutPresenter();
    }
}
