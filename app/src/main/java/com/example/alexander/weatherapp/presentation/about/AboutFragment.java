package com.example.alexander.weatherapp.presentation.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.baseviews.BaseFragment;
import com.example.alexander.weatherapp.di.modules.AboutModule;

import javax.inject.Inject;


public class AboutFragment extends BaseFragment implements AboutView {

    @Inject
    AboutPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.getAppComponent().plus(new AboutModule()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
