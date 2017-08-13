package com.example.alexander.weatherapp.presentation.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.baseviews.MvpPreferenceFragment;
import com.example.alexander.weatherapp.data.prefs.SharedPrefs;
import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.utils.LogUtils;

import javax.inject.Inject;

import butterknife.BindView;


public class SettingsFragment extends MvpPreferenceFragment implements SettingsView, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    SettingsPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @ProvidePresenter
    SettingsPresenter provideSettingsPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.getAppComponent().plus(new SettingsModule()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName(SharedPrefs.COMMON_PREFS);
        setPreferencesFromResource(R.xml.app_prefs, rootKey);
    }


    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar(getString(R.string.settings));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(SharedPrefs.AUTO_REFRESH)) {
            ListPreference dataPref = (ListPreference) findPreference(SharedPrefs.UPDATE_INTERVAL);
            if (dataPref.getValue() == null) {
                dataPref.setValueIndex(0); //set to index of your deafult value
            } else
                presenter.updateWeatherJob(sharedPreferences.getBoolean(SharedPrefs.AUTO_REFRESH, false));


        } else if (key.equals(SharedPrefs.UPDATE_INTERVAL)) {

            presenter.updateWeatherJob(sharedPreferences.getBoolean(SharedPrefs.AUTO_REFRESH, false));
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getPreferenceManager().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getPreferenceManager().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void jobStateChanged(boolean enabled) {
        //
    }

    @Override
    public void onJobError(Throwable cause) {
        LogUtils.write("Error start job:" + cause);
    }
}
