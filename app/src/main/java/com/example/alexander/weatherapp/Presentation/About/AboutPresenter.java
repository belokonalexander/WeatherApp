package com.example.alexander.weatherapp.Presentation.About;

import com.example.alexander.weatherapp.Presentation.About.Interfaces.IAboutPresenter;
import com.example.alexander.weatherapp.Presentation.About.Interfaces.IAboutView;

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
