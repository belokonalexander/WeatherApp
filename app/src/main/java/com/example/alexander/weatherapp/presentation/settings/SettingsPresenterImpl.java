package com.example.alexander.weatherapp.presentation.settings;

import com.example.alexander.weatherapp.business.settings.SettingsInteractor;
import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsPresenter;
import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexander on 08.07.2017.
 */

public class SettingsPresenterImpl implements SettingsPresenter {

    private SettingsView view;
    private SettingsInteractor interactor;

    public SettingsPresenterImpl(SettingsInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void bindView(SettingsView settingsView) {
        this.view = settingsView;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void updateWeatherJob(boolean enabled) {

            interactor.startUpdateJob(enabled).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(someresult -> handleSuccessJobStart(enabled), this::handleFailureJobStart);

    }

    @Override
    public void handleSuccessJobStart(Boolean enabled) {
        if(view!=null)
            view.jobStateChanged(enabled);
    }

    @Override
    public void handleFailureJobStart(Throwable throwable) {
        if(view!=null)
            view.onJobError(throwable);
    }
}
