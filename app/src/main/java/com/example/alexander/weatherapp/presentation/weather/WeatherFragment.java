package com.example.alexander.weatherapp.presentation.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.baseviews.BaseFragment;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.weather.Forecast;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.presentation.exceptions.ViewException;
import com.example.alexander.weatherapp.view.WeatherWidget;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class WeatherFragment extends BaseFragment implements WeatherView {

    private static final String CITY_ID = "CITY_ID";

    @Inject
    @InjectPresenter
    WeatherPresenter presenter;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.weather_widget)
    WeatherWidget weatherWidget;

    @BindView(R.id.forecast_list)
    RecyclerView forecastList;

    private ForecastAdapter forecastAdapter;

    public static WeatherFragment newInstance(int cityId) {
        WeatherFragment weatherFragment = new WeatherFragment();

        Bundle args = new Bundle();
        args.putInt(CITY_ID, cityId);
        weatherFragment.setArguments(args);

        return weatherFragment;
    }

    @ProvidePresenter
    WeatherPresenter provideWeatherPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication
                .getAppComponent()
                .plus(new WeatherModule())
                .inject(this);

        super.onCreate(savedInstanceState);


        presenter.setCityId(getArguments().getInt(CITY_ID));
        presenter.update();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshLayout.setOnRefreshListener(presenter::update);

        forecastList.setLayoutManager(new LinearLayoutManager(getContext()));
        forecastAdapter = new ForecastAdapter();
        forecastList.setAdapter(forecastAdapter);
    }

    @Override
    public void onError(Throwable cause) {
        String message = new ViewException(getContext(), cause).getDetailMessage();
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showWeather(CityWeather cityWeather) {
        getActivity().setTitle(cityWeather.getCityName());
        weatherWidget.setCityWeather(cityWeather);
    }

    @Override
    public void startProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void finishProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showForecast(List<Forecast> forecastList) {
        forecastAdapter.setForecastList(forecastList);
    }
}
