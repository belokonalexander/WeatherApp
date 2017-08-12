package com.example.alexander.weatherapp.presentation.add_city;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;

import java.util.List;

public interface AddCityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updatePredictions(List<Prediction> predictions);
}
