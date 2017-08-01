package com.example.alexander.weatherapp.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.alexander.weatherapp.MainActivity;
import com.example.alexander.weatherapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WeatherFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldShowAllViews() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_widget)).check(matches(isDisplayed()));
        onView(withId(R.id.city_autocomplete)).check(matches(isDisplayed()));
        onView(withId(R.id.swiperefresh)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAutoCompleteTextView() {
        onView(withId(R.id.city_autocomplete)).perform(typeText("Test")).check(matches(withText("Test")));
    }
}
