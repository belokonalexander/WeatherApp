package com.example.alexander.weatherapp.presentation.weather.models;

import java.io.Serializable;
import java.util.Date;

public class CityWeather implements Serializable {

    public static final int STATE_UNKNOWN = -1;
    public static final int STATE_RAIN = 0;
    public static final int STATE_CLOUD = 1;
    public static final int STATE_THUNDERSTORM = 2;
    public static final int STATE_SNOW = 3;
    public static final int STATE_CLEAR = 5;


    private int cityId;
    private int weatherState;
    private String cityName;
    private double temp;
    private double press;
    private int hum;
    private Date createdDate;

    public CityWeather(int cityId, int weatherState, String cityName, double temp, double press, int hum) {
        this.cityId = cityId;
        this.weatherState = weatherState;
        this.cityName = cityName;
        this.temp = temp;
        this.press = press;
        this.hum = hum;
        this.createdDate = new Date();
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public double getTemp() {
        return temp;
    }

    public double getPress() {
        return press;
    }

    public int getHum() {
        return hum;
    }

    public int getWeatherState() {
        return weatherState;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public static CityWeather getNullable() {
        return new CityWeather(-1, -1, "", -1d, -1d, -1);
    }

    public static boolean isNullable(CityWeather instance) {
        return instance == null || instance.equals(getNullable());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityWeather that = (CityWeather) o;

        if (cityId != that.cityId) return false;
        if (Double.compare(that.temp, temp) != 0) return false;
        if (Double.compare(that.press, press) != 0) return false;
        if (hum != that.hum) return false;
        if (weatherState != that.weatherState) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        return createdDate != null ? createdDate.equals(that.createdDate) : that.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + cityId;
        temp1 = Double.doubleToLongBits(temp);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(press);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + hum;
        result = 31 * result + weatherState;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "cityName='" + cityName + '\'' +
                ", cityId=" + cityId +
                ", temp=" + temp +
                ", press=" + press +
                ", hum=" + hum +
                ", weatherState=" + weatherState +
                ", createdDate=" + createdDate +
                '}';
    }


}
