package com.example.alexander.weatherapp.baseviews;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpDelegate;
import com.example.alexander.weatherapp.NavigationManager;
import com.example.alexander.weatherapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class MvpPreferenceFragment extends PreferenceFragmentCompat {


    private Unbinder unbinder;
    private NavigationManager navigationManager;

    protected abstract Toolbar getToolbar();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    public void initToolbar(String title) {
        getToolbar().setTitle(title);
        Drawable toolbarNavigationIcon = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu_black_24dp, null);
        navigationManager.setNavigationDrawerState(true);
        getToolbar().setNavigationOnClickListener(v -> navigationManager.openNavigationDrawer());
        initToolbarView(toolbarNavigationIcon);
    }

    private void initToolbarView(Drawable navigationIcon) {
        getToolbar().setNavigationIcon(navigationIcon);
        getToolbar().setContentInsetStartWithNavigation(0);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigationManager = (NavigationManager) getActivity();
    }

    private boolean mIsStateSaved;
    private MvpDelegate<? extends MvpPreferenceFragment> mMvpDelegate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        mIsStateSaved = false;

        getMvpDelegate().onAttach();
    }

    public void onResume() {
        super.onResume();

        mIsStateSaved = false;

        getMvpDelegate().onAttach();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mIsStateSaved = true;

        getMvpDelegate().onSaveInstanceState(outState);
        getMvpDelegate().onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();

        getMvpDelegate().onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getMvpDelegate().onDetach();
        getMvpDelegate().onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //We leave the screen and respectively all fragments will be destroyed
        if (getActivity().isFinishing()) {
            getMvpDelegate().onDestroy();
            return;
        }

        // When we rotate device isRemoving() return true for fragment placed in backstack
        // http://stackoverflow.com/questions/34649126/fragment-back-stack-and-isremoving
        if (mIsStateSaved) {
            mIsStateSaved = false;
            return;
        }

        // See https://github.com/Arello-Mobile/Moxy/issues/24
        boolean anyParentIsRemoving = false;
        Fragment parent = getParentFragment();
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving();
            parent = parent.getParentFragment();
        }

        if (isRemoving() || anyParentIsRemoving) {
            getMvpDelegate().onDestroy();
        }
    }

    /**
     * @return The {@link MvpDelegate} being used by this Fragment.
     */
    public MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }

        return mMvpDelegate;
    }
}
