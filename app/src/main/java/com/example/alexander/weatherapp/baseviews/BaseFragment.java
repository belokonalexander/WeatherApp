package com.example.alexander.weatherapp.baseviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends MvpAppCompatFragment {

    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
