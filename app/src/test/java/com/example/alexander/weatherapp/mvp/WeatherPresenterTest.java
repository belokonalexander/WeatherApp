package com.example.alexander.weatherapp.mvp;

import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.models.places.Location;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.presentation.weather.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.WeatherView$$State;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherPresenterTest {

    @Mock
    WeatherInteractor weatherInteractor;
    @Mock
    EventBus eventBus;
    @Mock
    WeatherView$$State viewState;

    private WeatherPresenter presenter;
    private CityWeather moscowWeather;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = new WeatherPresenter(weatherInteractor, eventBus);
        presenter.setViewState(viewState);

        moscowWeather = CityWeather.newInstance(524901, CityWeather.STATE_CLEAR, "Moscow", 20, 1000, 80);

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void onFirstAttachShouldShowWeather() {
        when(weatherInteractor.getWeather(anyBoolean())).thenReturn(Observable.just(moscowWeather));
        doNothing().when(eventBus).register(presenter);

        presenter.onFirstViewAttach();

        verify(viewState).startProgress(false);
        verify(viewState).showWeather(moscowWeather);
        verify(viewState, never()).onError(any(Throwable.class));
        verify(weatherInteractor).getWeather(true);
    }

    @Test
    public void shouldShowErrorWhenGetWeather() {
        when(weatherInteractor.getWeather(anyBoolean())).thenReturn(Observable.error(new Throwable()));

        presenter.getWeather(true);

        verify(viewState).startProgress(true);
        verify(viewState, never()).showWeather(any(CityWeather.class));
        verify(viewState).onError(any(Throwable.class));
        verify(weatherInteractor).getWeather(anyBoolean());
    }

    @Test
    public void shouldShowPredictions() {
        Prediction prediction = new Prediction();
        prediction.setPlaceId("123456");
        prediction.setDescription("Moscow");
        List<Prediction> predictions = Collections.singletonList(prediction);

        when(weatherInteractor.getAutocomplete("Moscow")).thenReturn(Single.just(predictions));

        presenter.getAutocomplete("Moscow");

        verify(viewState).showPredictions(predictions);
        verify(viewState, never()).onError(any(Throwable.class));
        verify(weatherInteractor).getAutocomplete("Moscow");
    }

    @Test
    public void shouldShowErrorWhenGetAutocomplete() {
        when(weatherInteractor.getAutocomplete(anyString())).thenReturn(Single.error(new Throwable()));

        presenter.getAutocomplete("Moscow");

        verify(viewState, never()).showPredictions(any());
        verify(viewState).onError(any(Throwable.class));
        verify(weatherInteractor).getAutocomplete("Moscow");
    }

    @Test
    public void ShouldShowWeatherByPlaceId() {
        Location location = new Location();
        location.setLatitude(4.19);
        location.setLongitude(4.20);
        when(weatherInteractor.getLocation("123456")).thenReturn(Single.just(location));
        when(weatherInteractor.getWeatherByLocation(location)).thenReturn(Single.just(moscowWeather));

        presenter.setPlace("123456");

        verify(viewState).showWeather(moscowWeather);
        verify(viewState, never()).onError(any(Throwable.class));
        verify(weatherInteractor).getLocation("123456");
        verify(weatherInteractor).getWeatherByLocation(location);
    }
}
