package com.example.alexander.weatherapp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.local.model.CityWeather;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.alexander.weatherapp.data.local.model.CityWeather.STATE_CLEAR;
import static com.example.alexander.weatherapp.data.local.model.CityWeather.STATE_CLOUD;
import static com.example.alexander.weatherapp.data.local.model.CityWeather.STATE_RAIN;
import static com.example.alexander.weatherapp.data.local.model.CityWeather.STATE_SNOW;
import static com.example.alexander.weatherapp.data.local.model.CityWeather.STATE_THUNDERSTORM;

public class WeatherWidget extends CardView {

    private static double KELVIN_TO_CESIUS = -273.15;

    @BindView(R.id.temperature)
    TextView temperature;

    @BindView(R.id.humidity)
    TextView humidity;

    @BindView(R.id.pressure)
    TextView pressure;

    @BindView(R.id.weather_icon)
    ImageView weatherIcon;

    public WeatherWidget(Context context) {
        super(context);
        init();
    }

    public WeatherWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeatherWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_weather, this);
        ButterKnife.bind(this);
    }

    public void setCityWeather(CityWeather cityWeather) {
        setTemperature(cityWeather.getTemp());
        setHumidity(cityWeather.getHum());
        setPressure(cityWeather.getPress());
        setWeatherIcon(cityWeather.getWeatherState());
    }

    private void setTemperature(double temperature) {
        temperature += KELVIN_TO_CESIUS;
        String text = getContext().getString(R.string.temperature_celsius, Math.round(temperature));
        if (temperature > 0) {
            text = "+" + text;
        }
        this.temperature.setText(text);
    }

    private void setHumidity(int humidity) {
        String text = getContext().getString(R.string.humidity, humidity);
        this.humidity.setText(text);
    }

    private void setPressure(double pressure) {
        String text = getContext().getString(R.string.pressure, Math.round(pressure));
        this.pressure.setText(text);
    }

    private void setWeatherIcon(int weatherState) {
        Drawable drawable;
        switch (weatherState) {
            case STATE_RAIN:
                drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_rain, null);
                break;
            case STATE_CLEAR:
                drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_sun, null);
                break;
            case STATE_CLOUD:
                drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_cloudy, null);
                break;
            case STATE_THUNDERSTORM:
                drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_storm, null);
                break;
            case STATE_SNOW:
                drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_snow, null);
                break;
            default:
                drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_cloudy, null);
                break;
        }
        weatherIcon.setImageDrawable(drawable);
    }
}
