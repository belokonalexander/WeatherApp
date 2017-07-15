package com.example.alexander.weatherapp.presentation.weather;

import com.example.alexander.weatherapp.Utils.LogUtils;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.events.StoreUpdatedEvent;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherView;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        if(view!=null) {
            view.showWeather(weather);
            view.finishProgress();
        }
    }

    @Override
    public void handleFailureGetWeather(Throwable throwable) {
        if(view!=null && !(throwable instanceof NullPointerException)) {
            LogUtils.write(" ---> error " + throwable);
            view.finishProgress();
        }
    }

    @Override
    public void getWeather() {
        if(view!=null) {
            view.startProgress();
        }
        weatherInteractor.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather);
    }

    @Override
    public void bindView(WeatherView weatherView) {
        this.view = weatherView;
        EventBus.getDefault().register(this);
        //отображение кешированных данных
        if (cachedCityWeatherModel != null) {
                view.showWeather(cachedCityWeatherModel);
            } else {
                if(firstAttach) {
                    updateFromStore();
                }
                getWeather();
            }

        firstAttach = false;
    }

    @Override
    public void unbindView() {
        view = null;
        EventBus.getDefault().unregister(this);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateFromStoreListener(StoreUpdatedEvent event){
        LogUtils.write("EVENT -> " + event);
        updateFromStore();
    }

    private void updateFromStore(){
        weatherInteractor.getStoredWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather);
    }



}
