package com.example.alexander.weatherapp.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexander on 15.07.2017.
 */

public class TimeUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

    public static CharSequence getFormattedDate(Date createdDate, boolean firstSpace) {

        String start = firstSpace ? " " : "";

        return start+dateFormat.format(createdDate);
    }
}
