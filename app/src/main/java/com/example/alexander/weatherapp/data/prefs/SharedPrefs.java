package com.example.alexander.weatherapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;


public class SharedPrefs implements SharedPrefsRepository {

    public static final String COMMON_PREFS = "COMMON_PREFS";
    public static final String UPDATE_INTERVAL = "UPDATE_INTERVAL";
    public static final String AUTO_REFRESH = "AUTO_REFRESH";
    public static final String LAST_WEATHER_RESULT = "LAST_WEATHER_RESULT";

    private SharedPreferences commonSettings;
    private Context context;

    public SharedPrefs(Context context) {
        this.context = context;
        commonSettings = context.getSharedPreferences(COMMON_PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getUpdateEnabled() {
        return commonSettings.getBoolean(AUTO_REFRESH, false);
    }

    @Override
    public int getUpdateInterval() {
        return Integer.parseInt(commonSettings.getString(UPDATE_INTERVAL, context.getString(R.string.default_update_interval)));
    }
}
