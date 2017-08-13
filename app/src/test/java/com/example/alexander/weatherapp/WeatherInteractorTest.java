package com.example.alexander.weatherapp;

import com.example.alexander.weatherapp.Helpers.ApiDummyValues;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepositoryImpl;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class WeatherInteractorTest {


    private WeatherApi weatherApi;
    private WeatherApiRepository apiRepository;
    private WeatherModelToCityWeatherMapper weatherMapper = new WeatherModelToCityWeatherMapper();


    @Before
    public void beforeEachTest() {
        weatherApi = mock(WeatherApi.class);
        apiRepository = new WeatherApiRepositoryImpl(weatherApi);

    }


    private void mockWithCorrectAnswer(WeatherApi weatherApi, ApiDummyValues.Modify... modifies) {
        when(weatherApi.weatherByName("")).thenReturn(Single.fromCallable(() -> ApiDummyValues.getWeatherModel(modifies)));
    }


    private void mockWithCorrectAnswer(WeatherApi weatherApi) {
        when(weatherApi.weatherByName("")).thenReturn(Single.fromCallable(ApiDummyValues::getWeatherModel));
    }

    private void mockWithIncorrectAnswer(WeatherApi weatherApi) {
        when(weatherApi.weatherByName("")).thenReturn(Single.fromCallable(() -> {
            throw new RuntimeException("api crash");
        }));
    }


    @Test
    public void repositoryEmitCorrectValue() {

        TestObserver testObserver = TestObserver.create();

        mockWithCorrectAnswer(weatherApi);
        apiRepository.getWeatherByName("").subscribe(testObserver);

        testObserver.assertValueCount(1);
        testObserver.assertComplete();
    }

    @Test
    public void toCityWeatherMapperType() {
        TestObserver<CityWeather> testObserver = TestObserver.create();
        mockWithCorrectAnswer(weatherApi);
        final WeatherModel[] apiAnswer = new WeatherModel[1];
        apiRepository.getWeatherByName("").map(weatherModel -> {
            apiAnswer[0] = weatherModel;
            return weatherModel;
        }).flatMap(weatherMapper.toCityWeather("Moscow")).subscribe(testObserver);


        testObserver.assertValue(o -> {
            boolean res = true;
            res &= o.getCityName().equals(apiAnswer[0].getName());
            res &= o.getHum() == apiAnswer[0].getMain().getHumidity();
            res &= Double.compare(o.getPress(), apiAnswer[0].getMain().getPressure()) == 0;
            res &= Double.compare(o.getTemp(), apiAnswer[0].getMain().getTemp()) == 0;
            res &= o.getCityId() == apiAnswer[0].getId();
            return res;
        });
    }

    @Test
    public void mapperStateCodeCorrectWhenWeatherNull() {

        TestObserver<CityWeather> testObserver = TestObserver.create();
        mockWithCorrectAnswer(weatherApi, (ApiDummyValues.Modify) jsonObject -> jsonObject.remove("weather"));
        apiRepository.getWeatherByName("").flatMap(weatherMapper.toCityWeather("Moscow")).subscribe(testObserver);
        testObserver.assertValue(cityWeather -> cityWeather.getWeatherState() == CityWeather.STATE_UNKNOWN);
    }

    @Test
    public void mapperStateCodeCorrectWhenWeather_603() {

        TestObserver<CityWeather> testObserver = TestObserver.create();
        mockWithCorrectAnswer(weatherApi, (ApiDummyValues.Modify) jsonObject -> {
            jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().addProperty("id", "603");

        });

        apiRepository.getWeatherByName("").flatMap(weatherMapper.toCityWeather("Moscow")).subscribe(testObserver);
        testObserver.assertValue(cityWeather -> cityWeather.getWeatherState() == CityWeather.STATE_SNOW);
    }


}