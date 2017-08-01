package com.example.alexander.weatherapp.presentation.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.events.StoreUpdatedEvent;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {

    private final WeatherInteractor weatherInteractor;
    private final CompositeDisposable disposables;
    private Disposable weatherDisposable;


    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
        disposables = new CompositeDisposable();
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
        disposables.dispose();
    }

    private void handleSuccessGetWeather(CityWeather weather) {
        getViewState().showWeather(weather);
    }


    private void handleFailureGetWeather(Throwable throwable) {
        throwable.printStackTrace();
        getViewState().onError(throwable);
        getViewState().finishProgress();
    }


    void getWeather(boolean loud) {
        //don't start task if is already executing
        getViewState().startProgress(loud);
        if (weatherDisposable == null || weatherDisposable.isDisposed()) {
            weatherDisposable = weatherInteractor.getWeather(true)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread(), true)
                    .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather, this::onGetWeatherComplete);
            disposables.add(weatherDisposable);
        }
    }

    private void onGetWeatherComplete() {
        getViewState().finishProgress();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateFromStoreListener(StoreUpdatedEvent event) {
        updateFromStore();
    }

    private void updateFromStore() {
        disposables.add(weatherInteractor.getWeather(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather, this::onGetWeatherComplete));
    }

    void getAutocomplete(String query) {
        disposables.add(weatherInteractor.getAutocomplete(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showPredictions));
    }

    void setPlace(String placeId) {
        disposables.add(weatherInteractor.getLocation(placeId)
                .flatMap(weatherInteractor::getWeatherByLocation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessGetWeather, this::handleFailureGetWeather));
    }
}
