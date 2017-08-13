package com.example.alexander.weatherapp.business.weather;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.data.repositories.GooglePlacesApiRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.job.JobWrapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class WeatherInteractorImpl implements WeatherInteractor {

    private static final int DEFAULT_CITY_ID = 524901;

    private final WeatherApiRepository weatherApiRepository;
    private final WeatherLocalRepository weatherLocalRepository;
    private final GooglePlacesApiRepository googlePlacesApiRepository;
    private final WeatherModelToCityWeatherMapper weatherMapper;
    private final JobWrapper jobWrapper;

    public WeatherInteractorImpl(WeatherApiRepository weatherApiRepository,
                                 WeatherModelToCityWeatherMapper mapper,
                                 WeatherLocalRepository weatherLocalRepository,
                                 JobWrapper jobWrapper,
                                 GooglePlacesApiRepository googlePlacesApiRepository) {
        this.weatherApiRepository = weatherApiRepository;
        this.weatherMapper = mapper;
        this.weatherLocalRepository = weatherLocalRepository;
        this.jobWrapper = jobWrapper;
        this.googlePlacesApiRepository = googlePlacesApiRepository;
    }

    @Override
    public Observable<CityWeather> getWeather(boolean fresh) {

        Single<CityWeather> localDataWeather = weatherLocalRepository.getCityWeather();

        if (!fresh) {
            return localDataWeather.toObservable();
        }

        Single<CityWeather> remoteDataWeather = localDataWeather
                .map(CityWeather::getCityId)
                .map(id -> id <= 0 ? DEFAULT_CITY_ID : id)
                .flatMap(weatherApiRepository::getWeatherById)
                .flatMap(weatherMapper.toCityWeather(""))
                .flatMap(weatherLocalRepository::saveCityWeather)
                .doOnSuccess(cityWeather -> jobWrapper.tryToStartWeatherJob());

        return Single.concat(localDataWeather, remoteDataWeather).toObservable();
    }

    @Override
    public Observable<CityWeather> getWeatherByCityId(int cityId) {
        return weatherLocalRepository.getCityWeather(cityId)
                .flatMapObservable(cityWeather -> Observable.concat(
                        Observable.just(cityWeather),
                        weatherApiRepository.getWeatherById(cityId)
                                .flatMap(weatherMapper.toCityWeather(cityWeather.getCityName()))
                                .flatMap(weatherLocalRepository::saveCityWeather)
                                .toObservable())
                );
    }

    @Override
    public Single<CityWeather> getWeatherByLocation(String cityName, Location location) {
        return weatherApiRepository.getWeatherByLocation(location)
                .flatMap(weatherMapper.toCityWeather(cityName))
                .flatMap(weatherLocalRepository::saveCityWeather)
                .doOnSuccess(cityWeather -> jobWrapper.tryToStartWeatherJob());
    }

    @Override
    public Single<List<Prediction>> getAutocomplete(String query) {
        return googlePlacesApiRepository.getAutocomplete(query);
    }

    @Override
    public Single<Location> getLocation(String placeId) {
        return googlePlacesApiRepository.getLocation(placeId);
    }
}
