package com.example.alexander.weatherapp.presentation.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.widget.Toast;

import com.example.alexander.weatherapp.Utils.LogUtils;
import com.example.alexander.weatherapp.MainActivity;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.prefs.SharedPrefs;
import com.example.alexander.weatherapp.presentation.NavigationFragment;
import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsPresenter;
import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexander on 07.07.2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SettingsView, NavigationFragment, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    SettingsPresenter presenter;

    @Inject
    SharedPrefs prefs;

    private Toast toast;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.get(getContext()).getAppComponent().plus(new SettingsModule()).inject(this);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName(SharedPrefs.COMMON_PREFS);
        setPreferencesFromResource(R.xml.app_prefs, rootKey);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter.bindView(this);
        toast = Toast.makeText(getContext(),null,Toast.LENGTH_LONG);
        ((MainActivity)getActivity()).getToolbar().setTitle(getNavigationName());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView();
        unbinder.unbind();

    }

    @Override
    public String getNavigationName() {
        return getResources().getString(R.string.settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(SharedPrefs._AUTO_REFRESH)){
            //устанавливаю дефолтное значение для элемента
            ListPreference dataPref = (ListPreference) findPreference(SharedPrefs._UPDATE_INTERVAL);
            if(dataPref.getValue() == null){
                dataPref.setValueIndex(0); //set to index of your deafult value
            }

            presenter.updateWeatherJob(sharedPreferences.getBoolean(SharedPrefs._AUTO_REFRESH,false));

        } else if(key.equals(SharedPrefs._UPDATE_INTERVAL)) {
            presenter.updateWeatherJob(sharedPreferences.getBoolean(SharedPrefs._AUTO_REFRESH,false));
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
