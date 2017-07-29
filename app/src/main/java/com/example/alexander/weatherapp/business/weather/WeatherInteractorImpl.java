package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.data.repositories.GooglePlacesApiRepository;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.presentation.weather.models.CityWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public class WeatherInteractorImpl implements WeatherInteractor {


    private final WeatherApiRepository weatherApiRepository;
    private final SharedPrefsRepository sharedPrefsRepository;
    private final GooglePlacesApiRepository googlePlacesApiRepository;
    private final WeatherModelToCityWeatherMapper weatherMapper;
    private final JobWrapper jobWrapper;


    public WeatherInteractorImpl(WeatherApiRepository weatherApiRepository,
                                 WeatherModelToCityWeatherMapper mapper,
                                 SharedPrefsRepository sharedPrefs,
                                 JobWrapper jobWrapper,
                                 GooglePlacesApiRepository googlePlacesApiRepository) {
        this.weatherApiRepository = weatherApiRepository;
        this.weatherMapper = mapper;
        this.sharedPrefsRepository = sharedPrefs;
        this.jobWrapper = jobWrapper;
        this.googlePlacesApiRepository = googlePlacesApiRepository;
    }

    @Override
    public Observable<CityWeather> getWeather(boolean fresh) {

        List<Single<CityWeather>> tasks = new ArrayList<>();

        tasks.add(sharedPrefsRepository.getCityWeather());

        if (fresh) {
            tasks.add(weatherApiRepository.getWeatherByName("Moscow")
                    .flatMap(weatherMapper.toCityWeather())
                    .doOnSuccess(cityWeather -> {
                        sharedPrefsRepository.saveCityWeather(cityWeather);
                        jobWrapper.tryToStartWeatherJob();
                    }));
        }


        return Single.concat(tasks).toObservable();
    }

    @Override
    public Single<CityWeather> getWeatherByLocation(Location location) {
        return weatherApiRepository.getWeatherByLocation(location)
                .flatMap(weatherMapper.toCityWeather());
    }

    @Override
    public Single<List<Prediction>> getAutocomplete(String query) {
        return googlePlacesApiRepository.getAutocomplete(query, "en");
    }

    @Override
    public Single<Location> getLocation(String placeId) {
        return googlePlacesApiRepository.getLocation(placeId, "en");
    }
}
