package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.di.scopes.AboutScope;
import com.example.alexander.weatherapp.presentation.about.AboutPresenterImpl;
import com.example.alexander.weatherapp.presentation.about.interfaces.AboutPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander on 08.07.2017.
 */

@Module
public class AboutModule {

    @Provides
    @AboutScope
    AboutPresenter provideAboutPresenter(){
        return new AboutPresenterImpl();
    }

}
