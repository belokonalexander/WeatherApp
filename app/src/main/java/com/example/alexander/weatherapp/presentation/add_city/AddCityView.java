package com.example.alexander.weatherapp.presentation.add_city;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;

import java.util.List;

public interface AddCityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updatePredictions(List<Prediction> predictions);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(@StringRes int messageId);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onCitySaved(int cityId);
}
