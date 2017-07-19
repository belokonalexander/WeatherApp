package com.example.alexander.weatherapp.data.network;

import android.content.Context;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;

import java.net.UnknownHostException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * generates API instance
 */
public class NetworkService {

    public static <T> T getService(Context context, Class<T> service) {

        String[] meta;

        if (service == WeatherApi.class) {
            meta = context.getResources().getStringArray(R.array.weather_api);
        } else {
            throw new RuntimeException("Wrong api service type");
        }

        return createService(context, service, meta);
    }

    private static <T> T createService(Context context, Class<T> service, String[] meta) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(meta[0])
                .client(getBaseInterceptor(context, meta[1]))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(service);
    }

    /**
     * Basic interceptor
     * add api kay to the query
     *
     * @param key query for api
     * @return okhttp client
     */
    private static OkHttpClient getBaseInterceptor(Context context, String key) {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(context.getString(R.string.api_key_alias), key)
                    .build();


            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        return httpClient.addInterceptor(getNetworkConnectInterceptor(context)).build();
    }


    /**
     * checks network connections and do caching
     *
     * @param context
     * @return interceptor
     */
    private static Interceptor getNetworkConnectInterceptor(Context context) {
        return chain -> {

            Request request = chain.request();

            //проверяем, есть ли интернет соединение
            if (!NetworkUtils.isNetworkAvailable(context)) {
                throw new UnknownHostException("Unable to resolve host \"" + request.url().host() + "\"");
            }

            //выполняю реальный запрос к api
            Response response = null;
            response = chain.proceed(request);

            //ответ для кеширования
            ResponseBody responseBody = response.body();
            String responseBodyString = responseBody.string();

            //тут можно закешировать результат
            if (response.code() == 200) {
                //TODO
            }

            //создадаю новый response для отправки обработчику
            return response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes())).build();
        };
    }
}
