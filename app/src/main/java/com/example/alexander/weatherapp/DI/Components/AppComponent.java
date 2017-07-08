package com.example.alexander.weatherapp.DI.Components;

import com.example.alexander.weatherapp.DI.Modules.AboutModule;
import com.example.alexander.weatherapp.DI.Modules.AppModule;
import com.example.alexander.weatherapp.DI.Modules.SettingsModule;
import com.example.alexander.weatherapp.DI.Modules.WeatherModule;
import com.example.alexander.weatherapp.WeatherApplication;

import dagger.Component;

/**
 * Created by Alexander on 08.07.2017.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    AboutComponent plus(AboutModule aboutModule);
    SettingsComponent plus(SettingsModule settingsModule);
    WeatherComponent plus(WeatherModule weatherModule);

    void inject(WeatherApplication app);
}
