package com.example.alexander.weatherapp.presentation.about;

import com.example.alexander.weatherapp.presentation.about.interfaces.AboutPresenter;
import com.example.alexander.weatherapp.presentation.about.interfaces.AboutView;

/**
 * Created by Alexander on 08.07.2017.
 */

public class AboutPresenterImpl implements AboutPresenter {

    private AboutView view;

    @Override
    public void bindView(AboutView aboutView) {
        this.view = aboutView;
    }

    @Override
    public void unbindView() {
        view = null;
    }


}
