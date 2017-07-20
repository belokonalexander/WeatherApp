package com.example.alexander.weatherapp.presentation.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.baseviews.BaseFragment;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.presentation.exceptions.ViewException;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;
import com.example.alexander.weatherapp.utils.LogUtils;
import com.example.alexander.weatherapp.views.layouts.WeatherHolder;

import javax.inject.Inject;

import butterknife.BindView;


public class WeatherFragment extends BaseFragment implements WeatherView {

    @Inject
    @InjectPresenter
    WeatherPresenter presenter;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.weather_widget)
    WeatherHolder weatherHolder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Toast toast;

    @ProvidePresenter
    WeatherPresenter provideWeatherPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.get(getContext()).getAppComponent().plus(new WeatherModule()).inject(this);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toast = Toast.makeText(getContext(), null, Toast.LENGTH_LONG);

        initViewLogic();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar(getString(R.string.weather));
    }

    private void initViewLogic() {
        refreshLayout.setOnRefreshListener(() -> presenter.getWeather(true));
    }

    @Override
    public void onError(Throwable cause) {
        ViewException viewException = new ViewException(getContext(), cause);
        toast.setText(viewException.getDetailMessage());
        toast.show();
    }

    @Override
    public void showWeather(CityWeather weatherModel) {
        weatherHolder.setModelAndShow(weatherModel);
    }


    @Override
    public void startProgress(boolean loud) {
        if (loud)
            refreshLayout.setRefreshing(true);
        LogUtils.write("startProgress");
    }

    @Override
    public void finishProgress() {
        refreshLayout.setRefreshing(false);
        LogUtils.write("finishProgress");
    }


}
