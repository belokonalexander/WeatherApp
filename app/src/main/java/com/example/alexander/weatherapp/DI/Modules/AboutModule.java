package com.example.alexander.weatherapp.DI.Modules;

import com.example.alexander.weatherapp.DI.Scopes.AboutScope;
import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.Presentation.About.AboutPresenter;
import com.example.alexander.weatherapp.Presentation.About.Interfaces.IAboutPresenter;

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
