package com.example.alexander.weatherapp.baseviews;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.alexander.weatherapp.MainActivity;
import com.example.alexander.weatherapp.NavigationManager;
import com.example.alexander.weatherapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends MvpAppCompatFragment {

    private Unbinder unbinder;
    private NavigationManager navigationManager;

    protected abstract Toolbar getToolbar();

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigationManager = ((NavigationManager) getActivity());
    }

    public void initToolbar(String title) {
        getToolbar().setTitle(title);
        Drawable toolbarNavigationIcon;
        if (getArguments() != null && getArguments().getBoolean(MainActivity.NAVIGATION_BACKPRESS)) {
            navigationManager.setNavigationDrawerState(false);
            toolbarNavigationIcon = null;
            //TODO
        } else {
            toolbarNavigationIcon = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu_black_24dp, null);
            navigationManager.setNavigationDrawerState(true);
            getToolbar().setNavigationOnClickListener(v -> navigationManager.openNavigationDrawer());
        }

        initToolbarView(toolbarNavigationIcon);

    }

    private void initToolbarView(Drawable navigationIcon) {
        navigationIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.normal_text_color_light), PorterDuff.Mode.SRC_IN);
        getToolbar().setNavigationIcon(navigationIcon);
        getToolbar().setContentInsetStartWithNavigation(0);
    }
}
