package com.goyourfly.weather_library;

/**
 * Created by gaoyf on 14/12/27.
 */
public class Urls {
    public static final String BASE = "http://api.openweathermap.org";
    public static final String WEATHER = BASE + "/data/2.5/weather?q=";

    public static String getWeatherUrl(String cityName){
        return WEATHER + cityName;
    }
}
