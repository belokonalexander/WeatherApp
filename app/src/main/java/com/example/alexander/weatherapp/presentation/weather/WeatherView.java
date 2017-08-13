package com.example.alexander.weatherapp.presentation.weather;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.moxy.strategy.AddToEndWithCompressor;


interface WeatherView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void onError(Throwable cause);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showWeather(CityWeather weatherModel);

    @StateStrategyType(value = AddToEndWithCompressor.class, tag = "progress$do")
    void startProgress();

    @StateStrategyType(value = AddToEndWithCompressor.class, tag = "progress$stop")
    void finishProgress();
}
