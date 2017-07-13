package com.example.alexander.weatherapp.prefs.uiprefs.time;

import android.content.Context;
import android.content.res.TypedArray;

import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

import com.example.alexander.weatherapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Alexander on 12.07.2017.
 */

public class TimePreference extends DialogPreference {


    private int mTime;

    /**
     * Resource of the dialog layout
     */
    private int mDialogLayoutResId = R.layout.pref_dialog_time;

    public TimePreference(Context context) {
        // Delegate to other constructor
        this(context, null);
    }

    public TimePreference(Context context, AttributeSet attrs) {
        // Delegate to other constructor
        // Use the preferenceStyle as the default style
        this(context, attrs, R.attr.preferenceStyle);
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        // Delegate to other constructor
        this(context, attrs, defStyleAttr, defStyleAttr);
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        // Du custom stuff here
        // ...
        // read attributes etc.
    }

    /**
     * Gets the time from the Shared Preferences
     *
     * @return The current preference value
     */
    public int getTime() {
        return mTime;
    }

    /**
     * Saves the time to the SharedPreferences
     *
     * @param time The time to save
     */
    public void setTime(int time) {
        mTime = time;

        // Save to SharedPreference
        persistInt(time);
    }

    //

    /**
     * Called when a Preference is being inflated and the default value attribute needs to be read
     */
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // The type of this preference is Int, so we read the default value from the attributes
        // as Int. Fallback value is set to 0.
        return a.getInt(index, 0);
    }

    //


    @Override
    public CharSequence getSummary() {
        return String.valueOf(mTime) + " " +getContext().getResources().getString(R.string.short_minutes);
    }

    /**
     * Returns the layout resource that is used as the content View for the dialog
     */
    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }


    //

    /**
     * Implement this to set the initial value of the Preference.
     */
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        // If the value can be restored, do it. If not, use the default value.
        setTime(restorePersistedValue ?
                getPersistedInt(mTime) : (int) defaultValue);
    }

}
