package com.example.alexander.weatherapp.presentation.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.baseviews.BaseFragment;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.presentation.exceptions.ViewException;
import com.example.alexander.weatherapp.utils.LogUtils;
import com.example.alexander.weatherapp.views.layouts.WeatherHolder;
import com.jakewharton.rxbinding2.widget.RxAutoCompleteTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;


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

    @BindView(R.id.city_autocomplete)
    AutoCompleteTextView cityAutocomplete;

    private CompositeDisposable disposables;

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

        refreshLayout.setOnRefreshListener(() -> presenter.getWeather(true));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar(getString(R.string.weather));

        disposables = new CompositeDisposable();

        disposables.add(RxTextView.textChanges(cityAutocomplete)
                .map(CharSequence::toString)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(presenter::getAutocomplete));

        disposables.add(RxAutoCompleteTextView.itemClickEvents(cityAutocomplete)
                .map(event -> (Prediction) event.view().getAdapter().getItem(event.position()))
                .map(Prediction::getPlaceId)
                .subscribe(presenter::setPlace));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (disposables != null) {
            disposables.dispose();
        }
    }

    @Override
    public void onError(Throwable cause) {
        String message = new ViewException(getContext(), cause).getDetailMessage();
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void showPredictions(List<Prediction> predictions) {
        ArrayAdapter<Prediction> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, predictions);
        cityAutocomplete.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
