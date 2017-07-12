package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.di.modules.AboutModule;
import com.example.alexander.weatherapp.di.scopes.AboutScope;
import com.example.alexander.weatherapp.presentation.about.AboutFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = AboutModule.class)
@AboutScope
public interface AboutComponent {

    void inject(AboutFragment aboutFragment);

}
