package com.example.alexander.weatherapp.DI.Scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Alexander on 08.07.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AboutScope {
}
