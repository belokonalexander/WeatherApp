package com.example.alexander.weatherapp.presentation.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.example.alexander.weatherapp.LogUtils;
import com.example.alexander.weatherapp.MainActivity;
import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.prefs.SharedPrefs;
import com.example.alexander.weatherapp.prefs.uiprefs.time.TimePreference;
import com.example.alexander.weatherapp.prefs.uiprefs.time.TimePreferenceDialogFragmentCompat;
import com.example.alexander.weatherapp.presentation.NavigationFragment;
import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsPresenter;
import com.example.alexander.weatherapp.presentation.settings.interfaces.SettingsView;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;

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

    Unbinder unbinder;

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
            LogUtils.write("ПЕРЕКЛЮЧАТЕЛЬ УВЕДОМЛЕНИЙ");
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
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof TimePreference) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = TimePreferenceDialogFragmentCompat.newInstance(preference.getKey());
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(), "preference_dialog");
        }


        /*if (dialogFragment != null) {
            // The dialog was created (it was one of our custom Preferences), show the dialog for it

        } else {
            // Dialog creation could not be handled here. Try with the super method.
            super.onDisplayPreferenceDialog(preference);
        }*/
    }



}
