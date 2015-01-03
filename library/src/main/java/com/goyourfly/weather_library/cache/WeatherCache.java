package com.goyourfly.weather_library.cache;

import android.util.Log;

import com.google.gson.Gson;
import com.goyourfly.weather_library.ui.obj.Place;
import com.goyourfly.weather_library.ui.obj.Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/30.
 */
public class WeatherCache {
    public static final HashMap<Integer, Weather> mCache = new HashMap<>();

    public void addWeather(Weather weather) {
        Log.d("","WeatherCache addWeather:" + new Gson().toJson(weather) + ",woeid:" + weather.woeid);
        mCache.put(weather.woeid, weather);
    }

    public void removeWeather(int woeid) {
        mCache.remove(woeid);
    }

    public Observable<Weather> getWeather(final int woeid) {
        return Async.start(new Func0<Weather>() {
            @Override
            public Weather call() {
                Weather weather = mCache.get(woeid);
                Log.d("","WeatherCache getWeather:" + new Gson().toJson(weather) + ",woeid:" + woeid);
                return weather;
            }
        });
    }

    public int getCount() {
        return mCache.size();
    }


    public Observable<List<Weather>> getAll(final int woeid) {
        return Async.start(new Func0<List<Weather>>() {
            @Override
            public List<Weather> call() {
                List<Weather> list = new ArrayList<>();
                Iterator<Integer> iterator = mCache.keySet().iterator();
                while (iterator.hasNext()) {
                    list.add(mCache.get(iterator.next()));
                }
                return list;
            }
        });
    }

}
