package com.example.alexander.weatherapp.presentation.weather;

import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.data.network.models.Weather.WeatherModel;
import com.example.alexander.weatherapp.presentation.exceptions.ViewException;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexander on 08.07.2017.
 */

public class WeatherPresenterImpl implements WeatherPresenter {

    private WeatherView view;

    private WeatherInteractor weatherInteractor;

    public WeatherPresenterImpl(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    @Override
    public void handleSuccessGetWeather(WeatherModel weather) {
        view.showWeather(weather);
        view.finishProgress();
    }

    @Override
    public void handleFailureGetWeather(Throwable throwable) {
        view.onError(throwable);
        view.finishProgress();
    }

    @Override
    public void getWeather() {
        weatherInteractor.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather);
    }

    @Override
    public void bindView(WeatherView weatherView) {
        this.view = weatherView;
    }

    @Override
    public void unbindView() {
        view = null;
    }


}
