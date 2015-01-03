package com.goyourfly.freeweather;

import android.app.Application;

import com.goyourfly.weather_library.WeatherModule;

/**
 * Created by gaoyf on 14/12/30.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        WeatherModule.getInstance().init(this);
    }
}
