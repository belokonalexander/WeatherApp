package com.example.alexander.weatherapp.Fragments;

import android.support.v4.app.Fragment;

import com.example.alexander.weatherapp.LogUtils;

/**
 * Created by Alexander on 07.07.2017.
 */

public abstract class NavigationFragment extends Fragment{

    public abstract String getNavigationName();

    public NavigationFragment() {
        LogUtils.write("Создан фрагмент: " + getClass().getCanonicalName());
    }


}
