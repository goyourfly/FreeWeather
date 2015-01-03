package com.goyourfly.weather_library.utils;

/**
 * Created by gaoyf on 15/1/2.
 */
public class WeatherUtils {
    public static int getCTemp(int fTemp){
        return (int) (5.0f/9.0f * (fTemp - 32));
    }
}
