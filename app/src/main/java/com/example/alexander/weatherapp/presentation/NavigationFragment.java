package com.example.alexander.weatherapp.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.MainActivity;

/**
 * Created by Alexander on 07.07.2017.
 */

public abstract class NavigationFragment extends Fragment{

    public abstract String getNavigationName();

    public NavigationFragment() {
        LogUtils.write("Created fragment: " + getClass().getCanonicalName());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity)getActivity()).getToolbar().setTitle(getNavigationName());
    }
}
