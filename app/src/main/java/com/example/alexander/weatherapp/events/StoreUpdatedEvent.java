package com.example.alexander.weatherapp.events;

/**
 * Created by Alexander on 15.07.2017.
 */

public class StoreUpdatedEvent {

    private String key;

    public StoreUpdatedEvent(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "StoreUpdatedEvent{" +
                "key='" + key + '\'' +
                '}';
    }
}
