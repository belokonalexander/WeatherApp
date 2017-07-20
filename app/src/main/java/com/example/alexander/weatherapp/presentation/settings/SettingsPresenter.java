package com.example.alexander.weatherapp.presentation.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.weatherapp.business.settings.SettingsInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    private SettingsInteractor interactor;
    private Disposable jobDisposable;

    public SettingsPresenter(SettingsInteractor interactor) {
        this.interactor = interactor;
    }

    void updateWeatherJob(boolean enabled) {
        jobDisposable = interactor.startUpdateJob(enabled).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(someresult -> handleSuccessJobStart(enabled), this::handleFailureJobStart);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        jobDisposable.dispose();
    }

    private void handleSuccessJobStart(Boolean enabled) {
        getViewState().jobStateChanged(enabled);
    }

    private void handleFailureJobStart(Throwable throwable) {
        getViewState().onJobError(throwable);
    }
}
