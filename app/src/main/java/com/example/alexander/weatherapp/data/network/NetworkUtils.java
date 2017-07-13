package com.example.alexander.weatherapp.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Alexander on 13.07.2017.
 */

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        } catch (NullPointerException e) {

            //на некоторых устройствах возникает исключение в getState()
            //ниже описанный метод исправляет такое поведение
            //однако не тестировал второй способ отдельно от первого и не могу пока оставить только его

            try {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        return true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        return true;
                    }
                } else {
                    return false;
                }
            } catch (Exception e1) {
                return false;
            }
        }
        return false;
    }


}
