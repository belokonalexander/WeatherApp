package com.example.alexander.weatherapp.data.repositories;

import com.example.alexander.weatherapp.data.network.api.GooglePlacesApi;
import com.example.alexander.weatherapp.data.network.models.places.DetailsResponse;
import com.example.alexander.weatherapp.data.network.models.places.Geometry;
import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.places.Place;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.data.network.models.places.PredictionsResponse;

import java.util.List;

import io.reactivex.Single;

public class GooglePlacesApiRepositoryImpl implements GooglePlacesApiRepository {

    private final GooglePlacesApi googlePlacesApi;

    public GooglePlacesApiRepositoryImpl(GooglePlacesApi googlePlacesApi) {
        this.googlePlacesApi = googlePlacesApi;
    }

    @Override
    public Single<List<Prediction>> getAutocomplete(String query) {
        return googlePlacesApi.getAutocomplete(query)
                .map(PredictionsResponse::getPredictions);
    }

    @Override
    public Single<Location> getLocation(String placeId) {
        return googlePlacesApi.getDetails(placeId)
                .map(DetailsResponse::getPlace)
                .map(Place::getGeometry)
                .map(Geometry::getLocation);
    }
}
