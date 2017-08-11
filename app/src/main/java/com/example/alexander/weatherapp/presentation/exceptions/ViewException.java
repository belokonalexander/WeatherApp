package com.example.alexander.weatherapp.presentation.exceptions;

/**
 * Created by Alexander on 13.07.2017.
 */

import android.content.Context;

import com.example.alexander.weatherapp.R;

import java.net.UnknownHostException;
import java.util.NoSuchElementException;

/**
 * translate exceptions for views
 */
public class ViewException extends Exception {

    private String detailMessage;

    public String getDetailMessage() {
        return detailMessage;
    }

    public ViewException(Context context, Throwable err) {

        detailMessage = context.getString(R.string.unknown_error);

        if (err instanceof UnknownHostException) {
            detailMessage = context.getString(R.string.connection_error);
        } else if (err instanceof NoSuchElementException) {
            detailMessage = null;
        }
    }
}
