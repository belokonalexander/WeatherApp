package com.example.alexander.weatherapp.presentation.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.events.StoreUpdatedEvent;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;
import com.example.alexander.weatherapp.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {

    private WeatherInteractor weatherInteractor;
    private Disposable getWeatherDisposable;

    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        EventBus.getDefault().register(this);
        getWeather(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        getWeatherDisposable.dispose();
    }

    private void handleSuccessGetWeather(CityWeather weather) {
        getViewState().showWeather(weather);
    }


    private void handleFailureGetWeather(Throwable throwable) {
        getViewState().onError(throwable);
        getViewState().finishProgress();
    }


    void getWeather(boolean loud) {
        //не повторяю одну и ту же задачу по загрузке данных
        getViewState().startProgress(loud);
        if(getWeatherDisposable==null || getWeatherDisposable.isDisposed()) {
            getWeatherDisposable = weatherInteractor.getWeather(true)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread(),true)
                    .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather,this::onGetWeatherComplete);
        }
    }

    private void onGetWeatherComplete() {
        getViewState().finishProgress();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateFromStoreListener(StoreUpdatedEvent event){
        LogUtils.write("EVENT -> " + event);
        updateFromStore();
    }

    private void updateFromStore(){
        weatherInteractor.getWeather(false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather,this::onGetWeatherComplete);
    }

}
