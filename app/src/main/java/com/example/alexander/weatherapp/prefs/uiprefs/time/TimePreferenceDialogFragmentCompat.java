package com.example.alexander.weatherapp.prefs.uiprefs.time;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import com.example.alexander.weatherapp.R;

/**
 * Created by Alexander on 12.07.2017.
 */

public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    /**
     * The TimePicker widget
     */
    private TimePicker timePicker;

    /**
     * Creates a new Instance of the TimePreferenceDialogFragment and stores the key of the
     * related Preference
     *
     * @param key The key of the related Preference
     * @return A new Instance of the TimePreferenceDialogFragment
     */
    public static TimePreferenceDialogFragmentCompat newInstance(String key) {
        final TimePreferenceDialogFragmentCompat
                fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        timePicker = (TimePicker) view.findViewById(R.id.timepicker);

        // Exception: There is no TimePicker with the id 'edit' in the dialog.
        if (timePicker == null) {
            throw new IllegalStateException("Dialog view must contain a TimePicker with id 'edit'");
        }

        // Get the time from the related Preference
        Integer minutesAfterMidnight = null;
        DialogPreference preference = getPreference();
        if (preference instanceof TimePreference) {
            minutesAfterMidnight = ((TimePreference) preference).getTime();
        }

        // Set the time to the TimePicker
        if (minutesAfterMidnight != null) {
            int hours = minutesAfterMidnight / 60;
            int minutes = minutesAfterMidnight % 60;
            boolean is24hour = DateFormat.is24HourFormat(getContext());

            timePicker.setIs24HourView(is24hour);

            timePicker.setCurrentHour(hours);
            timePicker.setCurrentMinute(minutes);
        }
    }


    /**
     * Called when the Dialog is closed.
     *
     * @param positiveResult Whether the Dialog was accepted or canceled.
     */
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // Get the current values from the TimePicker
            int hours;
            int minutes;

                hours = timePicker.getCurrentHour();
                minutes = timePicker.getCurrentMinute();


            // Generate value to save
            int minutesAfterMidnight = (hours * 60) + minutes;


            // Save the value
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                TimePreference timePreference = ((TimePreference) preference);
                // This allows the client to ignore the user value.
                if (timePreference.callChangeListener(minutesAfterMidnight)) {
                    // Save the value
                    timePreference.setTime(minutesAfterMidnight);
                    timePreference.setSummary(String.valueOf(minutesAfterMidnight));
                }
            }

        }
    }
}