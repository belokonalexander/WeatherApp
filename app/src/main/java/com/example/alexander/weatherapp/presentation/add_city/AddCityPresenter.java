package com.example.alexander.weatherapp.presentation.add_city;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AddCityPresenter extends MvpPresenter<AddCityView> {

    private final WeatherInteractor weatherInteractor;

    public AddCityPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void getAutocomplete(String query) {
        weatherInteractor.getAutocomplete(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::updatePredictions);
    }
}
