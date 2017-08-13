package com.example.alexander.weatherapp.presentation.add_city;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.utils.ThrowableUtils;

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
                .subscribe(getViewState()::updatePredictions, this::onError);
    }

    public void savePlace(Prediction prediction) {
        weatherInteractor.getLocation(prediction.getPlaceId())
                .flatMap(location -> weatherInteractor.getWeatherByLocation(prediction.getCityName(), location))
                .map(CityWeather::getCityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onCitySaved, this::onError);
    }

    private void onError(Throwable throwable) {
        getViewState().showError(ThrowableUtils.getErrorMessage(throwable));
    }
}
