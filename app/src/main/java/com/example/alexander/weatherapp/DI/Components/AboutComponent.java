package com.example.alexander.weatherapp.DI.Components;

import com.example.alexander.weatherapp.DI.Modules.AboutModule;
import com.example.alexander.weatherapp.DI.Scopes.AboutScope;
import com.example.alexander.weatherapp.Presentation.About.AboutFragment;

import dagger.Subcomponent;

/**
 * Created by Alexander on 08.07.2017.
 */
@Subcomponent(modules = AboutModule.class)
@AboutScope
public interface AboutComponent {

    void inject(AboutFragment aboutFragment);

}
