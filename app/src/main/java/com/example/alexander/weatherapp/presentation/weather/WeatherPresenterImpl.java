package com.example.alexander.weatherapp.presentation.weather;

import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherView;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexander on 08.07.2017.
 */

public class WeatherPresenterImpl implements WeatherPresenter {

    private WeatherView view;
    private WeatherInteractor weatherInteractor;
    private CityWeather cachedCityWeatherModel;

    private boolean firstAttach = true;

    public WeatherPresenterImpl(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    @Override
    public void handleSuccessGetWeather(CityWeather weather) {
        cachedCityWeatherModel = weather;
        view.showWeather(weather);
        view.finishProgress();
    }

    @Override
    public void handleFailureGetWeather(Throwable throwable) {
        view.onError(throwable);
        LogUtils.write(" ---> error " + throwable);
        view.finishProgress();
    }

    @Override
    public void getWeather() {
        view.startProgress();
        weatherInteractor.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather);
    }

    @Override
    public void bindView(WeatherView weatherView) {
        this.view = weatherView;

        //отображение кешированных данных
        if (cachedCityWeatherModel != null) {
                view.showWeather(cachedCityWeatherModel);
            } else {


                if(firstAttach) {
                    weatherInteractor.getStoredWeather()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather);
                }

                getWeather();

            }

        firstAttach = false;
    }

    @Override
    public void unbindView() {
        view = null;
    }


}
