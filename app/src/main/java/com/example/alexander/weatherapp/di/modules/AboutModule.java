package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.di.scopes.AboutScope;
import com.example.alexander.weatherapp.presentation.about.AboutPresenter;
import com.example.alexander.weatherapp.presentation.about.interfaces.IAboutPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander on 08.07.2017.
 */

@Module
public class AboutModule {

    @Provides
    @AboutScope
    IAboutPresenter provideIAboutPresenter(){
        return new AboutPresenter();
    }

}
