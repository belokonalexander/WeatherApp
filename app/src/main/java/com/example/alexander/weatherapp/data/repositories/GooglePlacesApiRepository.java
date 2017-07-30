package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;

import java.util.List;

import io.reactivex.Single;

public interface GooglePlacesApiRepository {

    Single<List<Prediction>> getAutocomplete(String query);

    Single<Location> getLocation(String placeId);
}
