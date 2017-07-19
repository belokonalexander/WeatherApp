package com.example.alexander.weatherapp.views.layouts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;
import com.example.alexander.weatherapp.utils.TimeUtils;

import java.util.Locale;

import static com.example.alexander.weatherapp.presentation.weather.models.CityWeather.STATE_CLEAR;
import static com.example.alexander.weatherapp.presentation.weather.models.CityWeather.STATE_CLOUD;
import static com.example.alexander.weatherapp.presentation.weather.models.CityWeather.STATE_RAIN;
import static com.example.alexander.weatherapp.presentation.weather.models.CityWeather.STATE_SNOW;
import static com.example.alexander.weatherapp.presentation.weather.models.CityWeather.STATE_THUNDERSTORM;
import static com.example.alexander.weatherapp.presentation.weather.models.CityWeather.STATE_UNKNOWN;



/**
 * View, which shows weather
 */
public class WeatherHolder extends RelativeLayout {

    private CityWeather model;


    public void setModelAndShow(@Nullable CityWeather model) {
        if(!CityWeather.isNullable(model)) {
            this.model = model;
            inflateContent();
        }
    }

    private void inflateContent(){
        clearView();

        LayoutInflater layoutInflater = (LayoutInflater ) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) layoutInflater.inflate(R.layout.weather_widget_layout, null);

        ImageView weatherIcon = (ImageView) layout.findViewById(R.id.weather_icon);
        TextView cityTextView = (TextView) layout.findViewById(R.id.city);
        TextView temperatureTextView = (TextView) layout.findViewById(R.id.temperature);
        TextView pressureTextView = (TextView) layout.findViewById(R.id.pressure);
        TextView humidityTextView = (TextView) layout.findViewById(R.id.humidity);
        TextView updatedAtTextView = (TextView) layout.findViewById(R.id.update_time);

        cityTextView.setText(getTranslatableCity(model.getCityId()));
        weatherIcon.setImageDrawable(getDrawableByStateCode(model.getWeatherState()));

        TypedValue pressCoef  = new TypedValue();
        getResources().getValue(R.dimen.press_coef, pressCoef,true);
        int press = (int) (model.getPress()*pressCoef.getFloat());
        String pressure = String.valueOf(press) + " " + getResources().getString(R.string.pressure);
        pressureTextView.setText(pressure);

        String hum = String.valueOf(model.getHum())+"%";
        humidityTextView.setText(hum);

        //В зависимости от региона (США или др, показывает время в кельвинах или цельсиях)
        temperatureTextView.setText(getLocaleTemp(model.getTemp()));

        updatedAtTextView.append(TimeUtils.getFormattedDate(model.getCreatedDate(), true));



        addView(layout);

    }

    private Drawable getDrawableByStateCode(Integer weatherState) {

        Drawable drawable = null;

        switch (weatherState){
            case STATE_RAIN:
                drawable = VectorDrawableCompat.create(getResources(),R.drawable.ic_rain,null);
                break;
            case STATE_CLEAR:
                drawable = VectorDrawableCompat.create(getResources(),R.drawable.ic_cloudy,null);
                break;
            case STATE_CLOUD:
                drawable = VectorDrawableCompat.create(getResources(),R.drawable.ic_cloudy,null);
                break;
            case STATE_THUNDERSTORM:
                drawable = VectorDrawableCompat.create(getResources(),R.drawable.ic_storm,null);
                break;
            case STATE_UNKNOWN:
                drawable =VectorDrawableCompat.create(getResources(),R.drawable.ic_cloudy,null);
                break;
            case STATE_SNOW:
                drawable = VectorDrawableCompat.create(getResources(),R.drawable.ic_snow,null);
                break;
        }

        return drawable;
    }

    private String getLocaleTemp(Double temp) {
        String tresult;
        boolean fahrenheit = false;
        String[] countriesWithFahrenheit = getResources().getStringArray(R.array.fahrenheit_countries);
        for(String country : countriesWithFahrenheit){
            if(country.equals(Locale.getDefault().getCountry())){
                fahrenheit = true;
                break;
            }
        }

        if(fahrenheit) {
            tresult = ((int)(temp*9/5-459.67)) + getResources().getString(R.string.fahrenheit);

        } else {
            float kelwinCoef = 273.15f;
            int ctemp = (int) (temp - kelwinCoef);
            String d = ctemp < 0 ? "-" : ctemp==0 ? "" :  "+";
            tresult = d + ctemp + getResources().getString(R.string.celsius);
        }

        return tresult;
    }

    private void clearView() {
        if(getChildCount()>0){
            removeAllViews();
        }
    }

    public WeatherHolder(Context context) {
        super(context);
    }

    public WeatherHolder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeatherHolder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public String getTranslatableCity(Integer cityId) {
        //TODO локализация города, пока только москва


        return getResources().getString(R.string.moscow);
    }
}
