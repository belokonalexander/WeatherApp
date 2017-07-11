package com.example.alexander.weatherapp.presentation.about;

import com.example.alexander.weatherapp.presentation.about.interfaces.IAboutPresenter;
import com.example.alexander.weatherapp.presentation.about.interfaces.IAboutView;

/**
 * Created by Alexander on 08.07.2017.
 */

public class AboutPresenter implements IAboutPresenter {

    private IAboutView view;

    @Override
    public void bindView(IAboutView aboutView) {
        this.view = aboutView;
    }

    @Override
    public void unbindView() {
        view = null;
    }


}
