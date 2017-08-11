package com.example.alexander.weatherapp.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.BuildConfig;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepositoryImpl;
import com.example.alexander.weatherapp.data.local.model.Models;
import com.example.alexander.weatherapp.data.prefs.SharedPrefs;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;


@Module
public class AppModule {

    private final Context appContext;


    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;

    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    SharedPrefsRepository provideSharedPrefsRepository() {
        return new SharedPrefs(appContext);
    }

    @Provides
    @Singleton
    WeatherModelToCityWeatherMapper provideMapper() {
        return new WeatherModelToCityWeatherMapper();
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    ReactiveEntityStore<Persistable> provideReactiveEntityStore(Context context) {
        DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 1);
        if (BuildConfig.DEBUG) {
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        return ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(source.getConfiguration()));
    }

    @Provides
    @Singleton
    WeatherLocalRepository provideWeatherRepository(ReactiveEntityStore<Persistable> entityStore) {
        return new WeatherLocalRepositoryImpl(entityStore);
    }
}
