package com.example.alexander.weatherapp.presentation.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.baseviews.BaseFragment;
import com.example.alexander.weatherapp.di.modules.AboutModule;

import javax.inject.Inject;

import butterknife.BindView;


public class AboutFragment extends BaseFragment implements AboutView {

    @Inject
    AboutPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar(getString(R.string.about));
    }
}
