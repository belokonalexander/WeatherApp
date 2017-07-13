package com.example.alexander.weatherapp.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexander on 12.07.2017.
 */

public class SharedPrefs {

    public static final String COMMON_PREFS = "common_prefs";
    public static final String _UPDATE_INTERVAL = "_update_interval";
    public static final String _AUTO_REFRESH = "_auto_refresh";

    private SharedPreferences commonSettings;

    public SharedPrefs(Context context)
    {
        commonSettings = context.getSharedPreferences(COMMON_PREFS,Context.MODE_PRIVATE);
    }

    public SharedPreferences getCommonSettings() {
        return commonSettings;
    }
}
