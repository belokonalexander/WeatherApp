package com.example.alexander.weatherapp.Presentation.About;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexander.weatherapp.DI.Modules.AboutModule;
import com.example.alexander.weatherapp.Presentation.About.Interfaces.IAboutPresenter;
import com.example.alexander.weatherapp.Presentation.About.Interfaces.IAboutView;
import com.example.alexander.weatherapp.Presentation.NavigationFragment;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Alexander on 07.07.2017.
 */

public class AboutFragment extends NavigationFragment implements IAboutView{

    @Inject
    IAboutPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.get(getContext()).getAppComponent().plus(new AboutModule()).inject(this);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.bindView(this);
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public String getNavigationName() {
        return getContext().getResources().getString(R.string.about);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView();
    }
}
