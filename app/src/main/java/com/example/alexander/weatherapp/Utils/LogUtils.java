package com.example.alexander.weatherapp.Utils;

import android.util.Log;

/**
 * Created by Alexander on 07.07.2017.
 */

public class LogUtils {

    public static final String common = "COMMON";

    public static void write(Object msg){
        Log.e(common, "-> " + msg);
    }

}
