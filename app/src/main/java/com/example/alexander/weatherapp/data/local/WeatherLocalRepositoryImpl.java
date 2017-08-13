package com.example.alexander.weatherapp.data.local;

import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.local.model.CityWeatherEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.requery.Persistable;
import io.requery.query.ResultDelegate;
import io.requery.reactivex.ReactiveEntityStore;

public class WeatherLocalRepositoryImpl implements WeatherLocalRepository {

    private final ReactiveEntityStore<Persistable> entityStore;

    public WeatherLocalRepositoryImpl(ReactiveEntityStore<Persistable> entityStore) {
        this.entityStore = entityStore;
    }

    @Override
    public Single<CityWeather> getCityWeather() {
        return entityStore
                .select(CityWeatherEntity.class)
                .limit(1)
                .get()
                .maybe()
                .toSingle()
                .cast(CityWeather.class);
    }

    public Observable<List<? extends CityWeather>> getAllCityWeather() {
        return entityStore
                .select(CityWeatherEntity.class)
                .orderBy(CityWeatherEntity.CREATED_DATE)
                .get()
                .observableResult()
                .map(ResultDelegate::toList);
    }

    @Override
    public Single<CityWeather> saveCityWeather(CityWeather cityWeather) {
        return entityStore
                .findByKey(CityWeatherEntity.class, cityWeather.getCityId())
                .toSingle()
                .flatMap(cityWeatherEntity -> entityStore.update((CityWeatherEntity) cityWeather))
                .onErrorResumeNext(throwable -> entityStore.insert((CityWeatherEntity) cityWeather))
                .cast(CityWeather.class);
    }
}
