package com.example.alexander.weatherapp.utils;

import android.support.annotation.StringRes;

import com.example.alexander.weatherapp.R;

import java.net.UnknownHostException;

public class ThrowableUtils {

    @StringRes
    public static int getErrorMessage(Throwable throwable) {
        int errorMessageId = R.string.unknown_error;
        if (throwable instanceof UnknownHostException) {
            errorMessageId = R.string.connection_error;
        }
        return errorMessageId;
    }
}
