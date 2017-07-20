package com.example.alexander.weatherapp.Helpers;

import com.example.alexander.weatherapp.data.network.models.weather.WeatherModel;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * for represent dummy weather open map api values
 */
public class ApiDummyValues {

    public interface Modify {
        void modifyJSON(JsonObject jsonObject);
    }

    public static WeatherModel getWeatherModel() {
        return getModelFromJSON(WeatherModel.class, getDefaultJSON());
    }

    public static WeatherModel getWeatherModel(Modify... modify) {
        return getModelFromJSON(WeatherModel.class, getAlteredWeatherModel(modify));
    }

    private static <T> T getModelFromJSON(Class<T> type, JsonObject json) {
        return new GsonBuilder().create().fromJson(json.toString(), type);
    }

    private static JsonObject getAlteredWeatherModel(Modify... modifies) {
        JsonObject jsonObject = getDefaultJSON();
        for (Modify modify : modifies) {
            modify.modifyJSON(jsonObject);
        }
        return jsonObject;
    }


    private static JsonObject getDefaultJSON() {
        return new JsonParser().parse(getWeatherModelJSON()).getAsJsonObject();
    }

    private static String getWeatherModelJSON() {

        return "{\n" +
                "\"coord\": {\n" +
                "\"lon\": 37.62,\n" +
                "\"lat\": 55.75\n" +
                "},\n" +
                "\"weather\": [\n" +
                "  {\n" +
                "\"id\": 803,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"description\": \"broken clouds\",\n" +
                "\"icon\": \"04d\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 292.9,\n" +
                "\"pressure\": 1015,\n" +
                "\"humidity\": 56,\n" +
                "\"temp_min\": 292.15,\n" +
                "\"temp_max\": 293.15\n" +
                "},\n" +
                "\"visibility\": 10000,\n" +
                "\"wind\": {\n" +
                "\"speed\": 3,\n" +
                "\"deg\": 250\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 75\n" +
                "},\n" +
                "\"dt\": 1500541200,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 7325,\n" +
                "\"message\": 0.0206,\n" +
                "\"country\": \"RU\",\n" +
                "\"sunrise\": 1500513265,\n" +
                "\"sunset\": 1500573379\n" +
                "},\n" +
                "\"id\": 524901,\n" +
                "\"name\": \"Moscow\",\n" +
                "\"cod\": 200\n" +
                "}";

    }
}
