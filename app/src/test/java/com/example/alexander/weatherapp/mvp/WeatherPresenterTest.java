package com.example.alexander.weatherapp.mvp;

import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.presentation.weather.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.WeatherView$$State;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
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

        verify(viewState).showWeather(moscowWeather);
        verify(viewState, never()).onError(any(Throwable.class));
        verify(weatherInteractor).getWeather(true);
    }
}
