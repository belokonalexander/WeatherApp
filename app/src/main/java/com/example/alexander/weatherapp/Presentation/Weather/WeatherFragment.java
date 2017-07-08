package com.example.alexander.weatherapp.Presentation.Weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexander.weatherapp.DI.Modules.WeatherModule;
import com.example.alexander.weatherapp.Presentation.NavigationFragment;
import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherPresenter;
import com.example.alexander.weatherapp.Presentation.Weather.Interfaces.IWeatherView;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Alexander on 07.07.2017.
 */

public class WeatherFragment extends NavigationFragment implements IWeatherView{

    @Inject
    IWeatherPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.get(getContext()).getAppComponent().plus(new WeatherModule()).inject(this);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.bindView(this);
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public String getNavigationName() {
        return getContext().getResources().getString(R.string.weather);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView();
    }
}
