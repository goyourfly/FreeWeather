package com.goyourfly.weather_library;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by gaoyf on 14/12/27.
 */
public class Urls {
    public static final String CODE = "utf-8";
    public static final String APPID = "04855f5e0cf44a9433ffd7b191a0650b63aebb66";
    public static final String BASE = "http://api.openweathermap.org";
    public static final String WEATHER = BASE + "/data/2.5/weather?q=";

    public static final String GET_WOEID = "http://where.yahooapis.com/v1/places.q('XXX')?appid=" + APPID + "&format=json";
    public static final String GET_WEATHER = "https://query.yahooapis.com/v1/public/yql?q=WOEID&appid=" + APPID + "&format=json";


    public static String getWoeidUrl(String cityName) {
        try {
            String result = GET_WOEID.replace("XXX", URLEncoder.encode(cityName, CODE));
            Log.d("", "WoeidUrl:" + result);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWeatherUrl(int woeid) {
        try {
            String result = GET_WEATHER.replace("WOEID", URLEncoder.encode("select * from weather.forecast where woeid=" + woeid, CODE));
            Log.d("", "WeatherUrl:" + result);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
