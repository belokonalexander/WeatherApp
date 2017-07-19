package com.example.alexander.weatherapp.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public static CharSequence getFormattedDate(Date createdDate, boolean firstSpace) {
        String start = firstSpace ? " " : "";
        return start+dateFormat.format(createdDate);
    }
}
