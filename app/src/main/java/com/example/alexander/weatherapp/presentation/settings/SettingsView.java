package com.example.alexander.weatherapp.presentation.settings;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;



@StateStrategyType(value = SkipStrategy.class)
interface SettingsView extends MvpView{

    void jobStateChanged(boolean enabled);
    void onJobError(Throwable cause);

}
