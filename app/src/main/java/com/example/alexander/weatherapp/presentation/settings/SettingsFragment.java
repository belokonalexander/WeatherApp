package com.example.alexander.weatherapp.presentation.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.presentation.NavigationFragment;
import com.example.alexander.weatherapp.presentation.settings.intefaces.ISettingsPresenter;
import com.example.alexander.weatherapp.presentation.settings.intefaces.ISettingsView;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexander on 07.07.2017.
 */

public class SettingsFragment extends NavigationFragment implements ISettingsView {

    @Inject
    ISettingsPresenter presenter;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.get(getContext()).getAppComponent().plus(new SettingsModule()).inject(this);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter.bindView(this);
    }

    @Override
    public String getNavigationName() {
        return getContext().getResources().getString(R.string.settings);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
        presenter.unbindView();
    }
}
